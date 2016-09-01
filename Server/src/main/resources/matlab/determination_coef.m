function R2 = determination_coef( x,y )
%UNTITLED3 Summary of this function goes here
%   Detailed explanation goes here
[a,b]=regressionEquation( x,y );
n=size(x,2);
ave_x=sum(x)/n;
ave_y=sum(y)/n;
SStot=sum((y-ave_y).^2);
SSreg=sum((b*x+a-ave_y).^2);
SSres=sum((y-b*x-a).^2);
R2=1-SSres/SStot;
end

