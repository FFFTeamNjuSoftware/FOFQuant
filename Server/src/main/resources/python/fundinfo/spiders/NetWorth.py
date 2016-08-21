# -*- coding:utf-8 -*-

from scrapy.spiders import Spider
from scrapy.selector import Selector
import re
import MySQLdb
from MySQLdb import IntegrityError
import datetime


class NetWorth(Spider):
    name = "netWorth"
    now = datetime.datetime.now().strftime("%Y-%m-%d")
    urlparttern = "http://jingzhi.funds.hexun.com/database/jzzs.aspx?fundcode=%s&startdate=%s&enddate=" + now
    allowed_domains = ["jingzhi.funds.hexun.com"]
    db = MySQLdb.connect("localhost", 'root', "123456", "fofquant", charset='utf8')
    start_urls = []
    cursor = db.cursor()
    cursor.execute("select code from fund_infos;")
    for line in cursor.fetchall():
        code = line[0][0:6]
        cursor = db.cursor()
        cursor.execute("select max(date) from net_worth where code='%s';" % code)
        dates = cursor.fetchall()
        if dates[0][0] is None:
            startdate = "1000-01-01"
        else:
            startdate = dates[0][0].strftime("%Y-%m-%d")
        start_urls.append(urlparttern % (code, startdate))
    sql = "INSERT INTO net_worth(date,code,unit_worth,total_worth,daily_rise) VALUES('%s','%s',%s,%s,%s);"

    def parse(self, response):
        sel = Selector(response)
        sites = sel.xpath('/html/body/div[9]/div[2]/table/tbody/tr')
        code = re.match('.*fundcode=(\d*)&.*', response.url).group(1)
        for site in sites:
            date = site.xpath('td[1]/text()').extract()[0]
            unitV = site.xpath('td[2]/text()').extract()[0]
            totalV = site.xpath('td[3]/text()').extract()[0]
            rise = site.xpath('td[4]/text()').extract()[0][:-1]
            try:
                print self.sql % (date, code, unitV, totalV, rise)
                self.cursor.execute(self.sql % (date, code, unitV, totalV, rise))
            except IntegrityError, e:
                print e
        self.db.commit()
