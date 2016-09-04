package util;

/**
 * Created by Seven on 16/9/4.
 */
public enum StrategyType {
    FUND_RISKY_PARITY("风险平价策略"),EQUAL("1/N策略"),MOMENTUM("动量策略"),MARKET_RISKY_PARITY("带杠杆的风险平价策略"),CPPI("CPPI策略");

    private String value;
    private StrategyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    }
