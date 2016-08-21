from scrapy.spiders import Spider
import MySQLdb
from scrapy import Selector
import re


class FundAllocation(Spider):
    db = MySQLdb.connect("localhost", 'root', "123456", "fofquant", charset='utf8')
    cursor = db.cursor()
    cursor.execute("select code from fund_infos;")

    name = "fundinfo"
    urlparttern = "http://fund.10jqka.com.cn/%s/interduce.html"
    allowed_domains = ["jingzhi.funds.hexun.com"]
    start_urls = []
    for line in cursor.fetchall():
        if line[0][0] != 'F':
            start_urls.append(urlparttern % (line[0]))

    def parse(self, response):
        sel = Selector(response)
        table = sel.xpath('/html/body/div[3]/div[3]/div[1]/div/div[1]/div[2]/table/tbody/tr')
        pattern = "(-*\d+\.*\d*).*"
        code = table[0].xpath('td[1]/text()').extract()[0].encode('utf-8')
        simple_name = table[0].xpath('td[2]/text()').extract()[0].encode('utf-8')
        fund_type = table[1].xpath('td[1]/text()').extract()[0].encode('utf-8')
        full_name = table[1].xpath('td[2]/text()').extract()[0].encode('utf-8')
        invest_type = table[2].xpath('td[1]/text()').extract()[0].encode('utf-8')
        establish_date = table[3].xpath('td[1]/text()').extract()[0].encode('utf-8')
        e_s_str = re.match(pattern, table[3].xpath('td[2]/text()').extract()[0].encode("utf-8"))
        s_str = re.match(pattern, table[4].xpath('td[2]/text()').extract()[0].encode("utf-8"))
        fee_str = re.match(pattern, table[4].xpath('td[1]/text()').extract()[0].encode('utf-8'))
        if e_s_str is not None:
            establish_scale = e_s_str.group(1)
        else:
            establish_scale = 'NULL'
        if s_str is not None:
            scale = s_str.group(1)
        else:
            scale = 'NULL'
        if fee_str is not None:
            manage_fee = fee_str.group(1)
        else:
            manage_fee = 'NULL'
        compare_base = table[8].xpath('td[2]/text()').extract()[0].encode('utf-8')
        invest_target = sel.xpath('/html/body/div[3]/div[3]/div[1]/div/div[2]/div/div[2]/p/text()').extract()[0].encode(
            "utf-8")
        invest_strategy = sel.xpath('/html/body/div[3]/div[3]/div[1]/div/div[3]/div/div[2]/p/text()').extract()[
            0].encode("utf-8")
        risk_feature = sel.xpath('/html/body/div[3]/div[3]/div[1]/div/div[5]/div/div[2]/p/text()').extract()[0].encode(
            "utf-8")
        sql = "update fund_infos set simple_name='%s',full_name='%s',fund_type='%s',establish_date='%s'," \
              "establish_scale=%s,scale=%s,manage_fee=%s,compare_base='%s',invest_target='%s'," \
              "invest_strategy='%s',risk_feature='%s',invest_type='%s' where code='%s';" % (
                  simple_name, full_name, fund_type, establish_date, establish_scale, scale, manage_fee, compare_base,
                  invest_target, invest_strategy, risk_feature, invest_type, code)
        FundAllocation.cursor.execute(sql)
        FundAllocation.db.commit()
