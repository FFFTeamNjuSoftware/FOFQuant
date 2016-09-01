function re = relatedIndex( base,data )
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here
n=size(base,2);
tem1=n*sum(base.*data)-sum(data)*sum(base);
tem2=(n*sum(data.*data)-sum(data)^2)^0.5;
tem3=(n*sum(base.*base)-sum(base)^2)^0.5;
re=tem1/(tem2*tem3);
end

