function[momw,momreturn,sharpe]=momentum(dataset,datafee,N,window,hold)
%dataset为标的资产的日收盘价数据,其中行数为天数,列数为标的资产个数 具体:N个权益类型基金构成的组合,M个固定收益类型基金构成的组合。
dataset=dataset(:,1:N);
datafee=datafee(:,1:3);
returndata=zeros(length(dataset)-1,N);
for i=1:N
    for j=1:(length(dataset)-1)
        returndata(j,i)=(dataset(j+1,i)-dataset(j,i))/dataset(j,i);
    end
end
%N为标的资产的个数,可相应更改,建立收益率矩阵
times=floor((length(dataset)-window)/hold);
momw=zeros(times,N);
for i=1:times
    meanret(i,:)=mean(returndata(1+hold*(i-1):window+hold*(i-1),:));
    smeanret(i,:)=sort(meanret(i,:),'descend');
    num(i)=size(find(smeanret(i,:)>=smeanret(i,min(N,5))),2);
    %将组合中基金窗口期平均收益率进行排序,然后将资金平均分配给窗口期历史平均ii率前5位的基金,即前5基金的权重均为1/5.并返回矩阵列数size(A,2) i
    momw(i,find(meanret(i,:)>=smeanret(i,num(i))))=1/num(i); 
    %注解:这里momw即风险平价中w
end
%注解,这里开始算收益率
for i=1:times
     for j=1:N
          dailyret(:,j)=returndata((window+1)+hold*(i-1):(window+hold)+hold*(i-1),j)+ones(hold,1);
          monthlyret(i,j)=prod(dailyret(:,j))-1-sum(datafee(j,:)'/100); 
     end
end
momreturn=sum((momw.*monthlyret)')';
sharpe=calSharpe((momreturn'),0.037,hold);
%注解,这里momreturn即风险平价里的rpturn 其余相同
end