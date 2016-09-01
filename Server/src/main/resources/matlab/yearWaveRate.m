function re = yearWaveRate(data)
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here
b=size(data,2);
re=starndarDeviation(data)*(252*(b-1))^0.5;
end
