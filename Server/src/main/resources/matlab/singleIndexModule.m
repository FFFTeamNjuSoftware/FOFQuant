function [ alpha,beta ] = singleIndexModule( base,data,Rf,T)
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here
n=size(base,2);
times=252/T;
yearProfit=sum(data)/n*times;
baseYearProfit=sum(base)/n*times;
ave_base=sum(base)/n;
ave_data=sum(data)/n;
cov_bd=sum((base-ave_base).*(data-ave_data))/(n-1);
var_base=sum((base-ave_base).*(base-ave_base))/(n-1);
beta=cov_bd/var_base;
alpha=(baseYearProfit-Rf)-beta*(yearProfit-Rf);
end

