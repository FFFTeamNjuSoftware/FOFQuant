function [ a,b ] =  regressionEquation( x,y )
%UNTITLED Summary of this function goes here
%   y=bx+a
n=size(x,2);
ave_x=sum(x)/n;
ave_y=sum(y)/n;
b=sum((x-ave_x).*(y-ave_y))/sum((x-ave_x).^2);
a=ave_y-b*ave_x;
end

