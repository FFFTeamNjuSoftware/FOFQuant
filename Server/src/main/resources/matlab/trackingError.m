function re = trackingError(base,data)
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here
fix_data=(data-base);
re=starndarDeviation(fix_data);
end

