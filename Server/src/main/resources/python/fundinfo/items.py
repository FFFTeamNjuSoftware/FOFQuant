# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class FundinfoItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    code = scrapy.Field()
    date = scrapy.Field()
    stock_value = scrapy.Field()
    stock_ratio = scrapy.Field()
    bond_value = scrapy.Field()
    bond_ratio = scrapy.Field()
    cash_value = scrapy.Field()
    cash_ratio = scrapy.Field()
    other_value = scrapy.Field()
    other_ratio = scrapy.Field()
    net_value = scrapy.Field()
    total_value = scrapy.Field()
    pass


class StockholdItem(scrapy.Item):
    date = scrapy.Field()
    fundCode = scrapy.Field()
    stockCode = scrapy.Field()
    stockName = scrapy.Field()
    holdNum = scrapy.Field()
    value = scrapy.Field()
    ratio = scrapy.Field()
    pass
