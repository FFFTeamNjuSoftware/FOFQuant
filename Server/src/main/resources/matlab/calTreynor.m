function re = calTreynor( data,base,Rf,T)
%UNTITLED3 Summary of this function goes here
%   Detailed explanation goes here
[alpha,beta]=singleIndexModule(base,data,Rf,T);
b=size(data,2);
times=252/T;
profit=sum(data)/b*times;
re=(profit-Rf)/beta;
end

