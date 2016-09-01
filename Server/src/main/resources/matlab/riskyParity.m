function[w,rpturn]=riskyParity(dataset,datafee,N,window,hold)
%读取标的资产的日收盘价数据
%其中行数为天数,列数为标的资产个数 
%具体:N 个权益类型基金构成的组合,M 个固定收益类型基金构成的组合。 
% N为标的资产的个数,可相应更改,建立收益率矩阵 
dataset=dataset(:,1:N);
datafee=datafee(:,1:3);
display(dataset);
display(datafee);
returndata=zeros(length(dataset)-1,N);
for i=1:N
    for j=1:(length(dataset)-1)
        returndata(j,i)=(dataset(j+1,i)-dataset(j,i))/dataset(j,i); 
%returndata (i,j)为第 i 个基金第j天的收益率 
    end
end
algow=1/N*ones(N,1);
%由数据天数、窗口期和持有期共同决定
%例如我们一共有 960 个日收益率数据,窗口期 为 360,持有期 30
%即每 30 天根据过去 360 天的收益率数据来调整权重,则(960-360)/ 30=20
%也即我们一共调整权重的次数。
adjust=floor((length(dataset)-window)/hold);
beta=zeros(1:N);
mul=zeros(1:N);
invbeta=zeros(1:N);
w=zeros(adjust,N);
for i=1:adjust
    orignalr=returndata(1+hold*(i-1):window+hold*(i-1),:); 
    algor=orignalr*algow;
    rmatrix=[orignalr,algor]; 
    covmatrix=cov(rmatrix);
    for j=1:N 
        beta(j)=covmatrix(j,N+1)/covmatrix(N+1,N+1); 
        mul(j)=algow(j)*beta(j)-1/N; 
        invbeta(j)=1/beta(j);
    end
    while sqrt(sum(mul.*mul))>0.003
        for j=1:N 
            beta(j)=covmatrix(j,N)/covmatrix(N+1,N+1); 
            mul(j)=algow(j)*beta(j)-1/N; 
            invbeta(j)=1/beta(j);
        end
        algow=invbeta/sum(invbeta); 
        algor=orignalr*algow'; 
        rmatrix=[orignalr,algor]; 
        covmatrix=cov(rmatrix);
        for t=1:N 
            beta(t)=covmatrix(t,N+1)/covmatrix(N+1,N+1); 
            mul(t)=algow(t)*beta(t)-1/N;
        end
    end
    w(i,1:N)=algow; 
    algow=1/N*ones(N,1);
end
%这里开始计算组合收益率
%datafee为N个基金的费率，注意与基金顺序保持一致
%回测暂且仅考虑托管费、管理费、销售服务费三种固定费率, 
%datafee 是 Nx3 的矩阵
% datafee=xlsread('d:/fundfee.xls');

for i=1:adjust
    for j=1:N 
    %N 个基金 
        dailyret(:,j)=returndata((window+1)+hold*(i-1):(hold+window)+hold*(i-1),j)+ones(hold,1);
        monthlyret(i,j)=prod(dailyret(:,j))-1-sum(datafee(j,:)');
        %收益率剔除基金费率得持有期的净利率
    end
end
rpturn=sum((w.*monthlyret)');
end