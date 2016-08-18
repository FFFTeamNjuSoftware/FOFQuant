function re = starndarDeviation( x )
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here
b=size(x,2);
ave=mean(x);
tem=x-ave;
re=(sum(tem.*tem)/(b-1))^0.5;

end

