function [ alpha,beta ] = singleIndexModule( x,y )
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here
n=size(x,2);
ave_x=mean(x);
ave_y=mean(y);
beta=(n*sum(x.*y)-sum(x)*sum(y))/(n*sum(x.*x)-sum(x)^2);
alpha=ave_y-beta*ave_x;
end

