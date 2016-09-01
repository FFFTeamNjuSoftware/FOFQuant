function sharpe = calSharpe( data,Rf )
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here
b=size(data,2);
yearProfit=sum(data)/b*252;
sharpe=(yearProfit-Rf)/yearWaveRate(data);
end

