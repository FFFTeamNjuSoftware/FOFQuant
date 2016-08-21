from scrapy.spiders import Spider
import MySQLdb
from scrapy import Selector
import re


class CompanyInfo(Spider):
    db = MySQLdb.connect("localhost", 'root', "123456", "fofquant", charset='utf8')
    cursor = db.cursor()
    cursor.execute("select company_id from company_info;")

    name = "companyInfo"
    urlparttern = "http://fund.10jqka.com.cn/company/%s/itsfund.html"
    allowed_domains = ['fund.10jqka.com.cn']
    start_urls = []
    for line in cursor.fetchall():
        start_urls.append(urlparttern % (line[0]))

    def parse(self, response):
        sel = Selector(response)
        company_id = re.match('.*company/(\w*)/.*', response.url).group(1)
        codes = sel.xpath('//*[@id="kfsbody"]/tr/td[2]/a/text()').extract()
        sql = "update fund_infos set company_id='%s' where code ='%s';"
        for code in codes:
            CompanyInfo.cursor.execute(sql % (company_id, code))
        CompanyInfo.db.commit()
