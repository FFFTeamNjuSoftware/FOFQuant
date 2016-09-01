function re = calSortino( data,Rf )
%UNTITLED3 Summary of this function goes here
%   Detailed explanation goes here
b=size(data,2);
ave_profit=sum(data)/b;
yearProfit=ave_profit*252;
fixData=min(data,0);
re=(yearProfit-Rf)/yearWaveRate(fixData);
end

