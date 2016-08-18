function re = noSystemRisk(x,y)
%UNTITLED4 Summary of this function goes here
%   Detailed explanation goes here
[alpha,beta]=singleIndexModule(x,y);
n=size(x,2);
re=((sum(y.*y)-alpha*sum(y)-beta*sum(x.*y))/(n-2))^0.5;
end

