function re = determination_coef( x,y )
%UNTITLED3 Summary of this function goes here
%   Detailed explanation goes here
length=size(x,2);
segema_x=starndarDeviation(x);
segema_y=starndarDeviation(y);
x_ave=mean(x);
y_ave=mean(y);
cov_xy=sum((x-x_ave).*(y-y_ave))/(length-1);
re=cov_xy/(segema_x*segema_y);
re=re^2;
end

