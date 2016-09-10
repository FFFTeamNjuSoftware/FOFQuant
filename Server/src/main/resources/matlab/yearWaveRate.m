function re = yearWaveRate(data,T)
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here
b=size(data,2);
times=252.0/T;
sd=starndarDeviation(data);
mul=(times)^0.5;
re=sd*mul;
end
