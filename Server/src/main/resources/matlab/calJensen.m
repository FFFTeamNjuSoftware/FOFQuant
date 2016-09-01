function re = calJensen( data,base,Rf,T )
%UNTITLED7 Summary of this function goes here
%   Detailed explanation goes here
[alpha,beta]=singleIndexModule(base,data,Rf,T);
b=size(data,2);
times=252/T;
baseYearProfit=sum(base)/b*times;
yearProfit=sum(data)/b*times;
re=baseYearProfit-Rf-(yearProfit-Rf)*beta;
end

