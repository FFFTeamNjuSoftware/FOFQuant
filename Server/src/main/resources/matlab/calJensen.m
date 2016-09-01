function re = calJensen( data,base,Rf )
%UNTITLED7 Summary of this function goes here
%   Detailed explanation goes here
[alpha,beta]=singleIndexModule(base,data);
b=size(data,2);
baseYearProfit=sum(base)/b*252;
yearProfit=sum(data)/b*252;
re=baseYearProfit-Rf-(yearProfit-Rf)*beta;
end

