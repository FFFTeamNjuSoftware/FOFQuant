function sharpe = calSharpe( data,Rf,T )
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here
b=size(data,2);
times=252/T;
yearProfit=sum(data)/b*times;
sharpe=(yearProfit-Rf)/yearWaveRate(data,T);
end

