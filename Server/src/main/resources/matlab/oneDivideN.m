function[equalw,equalwreturn,sharpe]=oneDivideN(dataset,datafee,N,window,hold)
%读取数据,为标的资产的日收盘价数据,其中行数为天数,列数为标的资产个数
%具体:N个权益类型基金构成的组合,M个固定收益类型基金构成的组合。 
% dataset=xlsread('d:/dailysector.xls');
dataset=dataset(:,1:N);
datafee=datafee(:,1:3);
returndata=zeros(length(dataset)-1,N);
for i=1:N
    for j=1:(length(dataset)-1) 
        returndata(j,i)=(dataset(j+1,i)-dataset(j,i))/dataset(j,i);
    end
end
%N为标的资产的个数,建立收益率矩阵 
%20由数据天数、窗口期和持有期共同决定
%例如我们一共有960个日收益率数据,窗口期为 360,持有期30
%即每30天根据过去360天的收益率数据来调整权重,则(960-360)/ 30=20
%也即我们一共调整权重的次数。窗口期与持有期也需回测。
adjust=floor((length(dataset)-window)/hold);
equalw=1/N*ones(adjust,N);
%注解:equal w即类似风险平价中的w
%注解,这里开始算收益率
 for i=1:adjust
     for j=1:N
         dailyret(:,j)=returndata((window+1)+hold*(i-1):(window+hold)+hold*(i-1),j)+ones(hold,1);
         monthlyret(i,j)=prod(dailyret(:,j))-1-sum(datafee(j,:)')/100;
     end
 end
equalwreturn=sum((equalw.*monthlyret)')';
sharpe=adjust((equalwreturn'),hold);
end