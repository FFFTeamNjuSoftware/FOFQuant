function[]=CPPITest()
PortValue=100;
%[2,2.5,3,3.5,4];
Riskmulti=4;
%[0.95,1.00];
GuarantRatio=0.95;
TradeDayTimeLong=250;
TradeDayOfYear=250;
adjustCycle=[1,5,10,20];
RisklessReturn=0.05;
TradeFee=0.005;
[date,rate]=textread('/Users/Seven/Programming/Matlab-workspace/sz.txt','%s%s');
SData=str2double(rate(length(date)-TradeDayTimeLong*3:length(date)-TradeDayTimeLong*2));
[F,E,A,G,SumTradeFee, portFreez]=CPPIStr(PortValue,Riskmulti,GuarantRatio,TradeDayTimeLong,TradeDayOfYear,adjustCycle,RisklessReturn,TradeFee,SData);
fid=fopen('/Users/Seven/Programming/Matlab-workspace/CPPI/szTestR4G0.95.txt','w');
fprintf(fid,'date          F            E            A             G\n');
for i=1:TradeDayTimeLong+1
    fprintf(fid,'%s    %f    %f    %f    %f\n',cell2mat(date(length(date)-TradeDayTimeLong*3-1+i)),F(i),E(i),A(i),G(i));
end
fprintf(fid,'SumTradeFee=%f   portFreez=%f',SumTradeFee,portFreez);
fclose(fid);
end