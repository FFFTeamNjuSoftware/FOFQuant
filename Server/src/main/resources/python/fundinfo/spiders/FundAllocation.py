from scrapy.spiders import Spider
import MySQLdb
from scrapy import Selector
from fundinfo.items import FundinfoItem
import re


class FundAllocation(Spider):
    name = "fundallocation"
    urlparttern = "http://fund.10jqka.com.cn/%s/allocation.html"
    allowed_domains = ["jingzhi.funds.hexun.com"]
    db = MySQLdb.connect("localhost", 'root', "123456", "fofquant", charset='utf8')
    start_urls = []
    cursor = db.cursor()
    cursor.execute("select code from fund_infos;")
    for line in cursor.fetchall():
        if line[0][0] != 'F':
            start_urls.append(urlparttern % (line[0]))

    def parse(self, response):
        sel = Selector(response)
        sites = sel.xpath('/html/body/div[3]/div[3]/div[1]/div/div[2]/div[2]/table/tbody/tr')
        items = []
        code = re.match('.*com\.cn/(\d*)/.*', response.url).group(1)
        for site in sites:
            item = FundinfoItem()
            item['date'] = site.xpath('td[1]/text()').extract()[0]
            item['code'] = code
            item['stock_value'] = float(site.xpath('td[2]/text()').extract()[0])
            item['stock_ratio'] = float(site.xpath('td[3]/text()').extract()[0][:-1])
            item['bond_value'] = float(site.xpath('td[4]/text()').extract()[0])
            item['bond_ratio'] = float(site.xpath('td[5]/text()').extract()[0][:-1])
            item['cash_value'] = float(site.xpath('td[6]/text()').extract()[0])
            item['cash_ratio'] = float(site.xpath('td[7]/text()').extract()[0][:-1])
            item['other_value'] = float(site.xpath('td[8]/text()').extract()[0])
            item['other_ratio'] = float(site.xpath('td[9]/text()').extract()[0][:-1])
            item['net_value'] = float(site.xpath('td[10]/text()').extract()[0])
            item['total_value'] = float(site.xpath('td[11]/text()').extract()[0])
            items.append(item)
        return items
