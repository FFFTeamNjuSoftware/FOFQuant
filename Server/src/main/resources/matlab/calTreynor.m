function re = calTreynor( data,base,Rf )
%UNTITLED3 Summary of this function goes here
%   Detailed explanation goes here
[alpha,beta]=singleIndexModule(base,data);
b=size(data,2);
profit=sum(data)/b*252;
re=(profit-Rf)/beta;
end

