function re = trackingError(base,data)
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here
fix_data=(data-base);
re=standarDeviation(fix_data);
end

