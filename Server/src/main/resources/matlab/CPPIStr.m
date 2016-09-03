function [F,E,A,G,SumTradeFee, portFreez] = CPPIStr(PortValue,Riskmulti,GuarantRatio,TradeDayTimeLong,TradeDayOfYear,adjustCycle,RisklessReturn,TradeFee,SData)

%input:
%PortValue 产品组合初始值
%Riskmulti:CPPI策略风险乘数
%GuarantRatio:产品保本率
%TradeDayTimeLong:产品期限，交易日
%TradeDayOfYear: 产品每年交易日，如250天
%adjustCycle:产品根据模型调整周期，例如每10个交易日调整一次。
%RisklessReturn: 无风险资产年化收益率
%TradeFee:交易费用
%SData: 模拟风险资产收益序列，布朗运动
%output:
%F:数组，第t个数据为t时刻安全底线
%E:数组，第t个数据为t时刻可投风险资产上限
%A:数组，第t个数据为t时刻产品净值
%G:数组，第t个数据为t时刻可投无g风险资产下限
%SumTradeFee：总交易费用
%portFeez:组合交易是否出现平仓，0未 1出现

SumTradeFee = 0;
% F,E,A,G length = N + 1
F = zeros(1, TradeDayTimeLong + 1);
E = zeros(1, TradeDayTimeLong + 1);
A = zeros(1, TradeDayTimeLong + 1);
G = zeros(1, TradeDayTimeLong + 1);

%初始组合资产
A(1) = PortValue;
%初始安全底线
F(1) = GuarantRatio * PortValue * exp(-RisklessReturn * TradeDayTimeLong/TradeDayOfYear);
%初始风险资产
E(1) = max(0, Riskmulti * (A(1)-F(1)));
%无风险资产
G(1) = A(1) - E(1);

%是否平仓 
portFreez = 0;
%逐日模拟
for i = 2: TradeDayTimeLong + 1
    E(i) = E(i-1) * (1 + (SData(i) - SData(i-1))/(1+SData(i-1)));
    G(i) = G(i-1) * (1 + RisklessReturn/TradeDayOfYear);
    A(i) = E(i) + G(i);
    F(i) = GuarantRatio * PortValue * exp(- RisklessReturn * (TradeDayTimeLong - i + 1)/TradeDayOfYear);
    
    %判断是否调仓
    if mod(i,adjustCycle) == 0 %定期调仓
        temp = E(i);
        E(i) = max(0, Riskmulti * (A(i) - F(i)));
        SumTradeFee = SumTradeFee + TradeFee * abs(E(i) - temp);
        G(i) = A(i) - E(i) - TradeFee * abs(E(i) - temp);
    end
    %判断是否平仓
    if E(i) == 0
        A(i) = G(i);
        portFreez = 1;
    end
end
