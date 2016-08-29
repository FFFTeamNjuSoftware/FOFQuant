function [ alpha,beta ] = singleIndexModule( base,data,Rf)
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here
n=size(base,2);
yearProfit=sum(data)/n*252;
baseYearProfit=sum(base)/n*252;
ave_base=sum(base)/n;
ave_data=sum(data)/n;
cov_bd=sum((base-ave_base).*(data-ave_data))/(n-1);
var_base=sum((base-ave_base).*(base-ave_base))/(n-1);
beta=cov_bd/var_base;
alpha=(baseYearProfit-Rf)-beta*(yearProfit-Rf);
end

