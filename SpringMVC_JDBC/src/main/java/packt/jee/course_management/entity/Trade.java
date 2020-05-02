package packt.jee.course_management.entity;

public class Trade {

    private long tradeId;
    private String buySell;
    private String buyCurrency;
    private String sellCurrency;
	public long getTradeId() {
		return tradeId;
	}
	public void setTradeId(long tradeId) {
		this.tradeId = tradeId;
	}
	public String getBuySell() {
		return buySell;
	}
	public void setBuySell(String buySell) {
		this.buySell = buySell;
	}
	public String getBuyCurrency() {
		return buyCurrency;
	}
	public void setBuyCurrency(String buyCurrency) {
		this.buyCurrency = buyCurrency;
	}
	public String getSellCurrency() {
		return sellCurrency;
	}
	public void setSellCurrency(String sellCurrency) {
		this.sellCurrency = sellCurrency;
	}

    //getters and setters

    
}