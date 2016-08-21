# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: http://doc.scrapy.org/en/latest/topics/item-pipeline.html
import json
import codecs
import MySQLdb


class FundinfoPipeline(object):
    def __init__(self):
        self.db = MySQLdb.connect("localhost", 'root', "123456", "fofquant", charset='utf8')
        self.cursor = self.db.cursor()
        self.count = 0
        # self.file = codecs.open('fundallocation.json', 'wb', encoding='utf-8')

    def process_item(self, item, spider):
        # line = json.dumps(dict(item)) + '\n'
        # print line
        # self.file.write(line.decode("unicode_escape"))
        sql = "INSERT INTO asset_allocation(date,code,stock_value,stock_ratio,bond_value,cash_value,cash_ratio," \
              "bond_ratio,other_value,other_ratio,net_value,total_value) " \
              "VALUES('%s','%s','%f','%f','%f','%f','%f','%f','%f','%f','%f','%f');"
        self.cursor.execute(sql % (
            item['date'], item['code'], item['stock_value'], item['stock_ratio'], item['bond_value'],
            item['bond_ratio'], item['cash_value'], item['cash_ratio'], item['other_value'], item['other_ratio'],
            item['net_value'], item['total_value']))
        self.db.commit()
        # self.count = self.count + 1
        # if (self.count % 500) == 0:

            # self.file.flush()
        return item
