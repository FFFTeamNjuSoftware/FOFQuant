function[w,rpturn,sharpe]=MarketRiskyParity(dataset,windowperiod,holdingperiod,lever)
%读取数据，为标的资产的日收盘价数据，其中行数为天数，第一列为股票基金指数，第二列为国泰上证5年国债etf
%returndata(j,2)后的*5为5倍杠杆的意思
for j=1:(length(dataset)-1)
    returndata(j,1)=(dataset(j+1,1)-dataset(j,1))/dataset(j,1);
    returndata(j,2)=(dataset(j+1,2)-dataset(j,2))/dataset(j,2)*lever;
end
%初始权重设置为等权重
algow=1/2*ones(2,1);
%w为权重向量，先设置为空
w=[];
times=floor((length(dataset)-windowperiod)/holdingperiod);
%{
20由回测数据天数、窗口期和持有期共同决定，例如我们一共有980个日收益率数据，窗口期为360，
持有期30，即每30天根据过去360天的收益率数据来调整权重，则向下取整（980-360）/30=20,也即我们一共调整权重的次数
%}
%以360天为窗口期，30天为持有期，建立协方差矩阵
for i=1:times
    orignalr=returndata(1+holdingperiod*(i-1):windowperiod+holdingperiod*(i-1),:);
    algor=orignalr*algow;
    rmatrix=[orignalr,algor];
    covmatrix=cov(rmatrix);
    %计算每个资产相对于组合的beta值
       for j=1:2
           beta(j)=covmatrix(j,3)/covmatrix(3,3);
           mul(j)=algow(j)*beta(j)-1/2;
           invbeta(j)=1/beta(j);
       end
       %判断是否退出迭代，当小于0.003时退出迭代，0.003自己取定，值越小，精度越高
       while sqrt(sum(mul.*mul))>0.003;
           for j=1:2
               beta(j)=covmatrix(j,3)/covmatrix(3,3);
               mul(j)=algow(j)*beta(j)-1/2;
               invbeta(j)=1/beta(j);
           end
           %当大于0.003时继续迭代，得到新的w值，并计算新的协方差矩阵，依次循环
           algow=invbeta/sum(invbeta);
           algor=orignalr*algow';
           rmatrix=[orignalr,algor];
           covmatrix=cov(rmatrix);
           for t=1:2
               beta(t)=covmatrix(t,3)/covmatrix(3,3);
               mul(t)=algow(t)*beta(t)-1/2;
           end
       end
       w(i,1:2)=algow;
       algow=1/2*ones(2,1);
end
%如果股票基金权重为负，人为调整为0权重
for i=1:times
    if w(i,1)<0;
        w(i,1)=0;
        w(i,2)=1;
    end
end
%求出日收益率矩阵和每30天的收益率率矩阵（30为持有期）
for i=1:times
    for j=1:2
        dailyret(:,j)=returndata((windowperiod+1)+holdingperiod*(i-1):(windowperiod+holdingperiod)+holdingperiod*(i-1),j)+ones(holdingperiod,1);
        monthlyret(i,j)=prod(dailyret(:,j))-1;
    end
end
%利用上述迭代得出的权重向量w和月收益率矩阵求得组合每30天的收益率矩阵（30为持有期）
rpturn=sum((w.*monthlyret)')';
sharpe=calSharpe((rpturn'),0.037,holdingperiod);    
end