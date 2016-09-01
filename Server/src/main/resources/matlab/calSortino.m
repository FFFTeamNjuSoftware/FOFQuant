function re = calSortino( data,Rf,T )
%UNTITLED3 Summary of this function goes here
%   Detailed explanation goes here
b=size(data,2);
ave_profit=sum(data)/b;
times=252/T;
yearProfit=ave_profit*times;
fixData=min(data,0);
re=(yearProfit-Rf)/yearWaveRate(fixData,T);
end

