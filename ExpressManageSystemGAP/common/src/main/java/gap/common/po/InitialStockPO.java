package gap.common.po;

import java.io.Serializable;

public class InitialStockPO implements Serializable {
	private String stockName;
	private int goodsNum;
	private double occupiedRate;
	
	public InitialStockPO(String stockName,int goodNum,double occupiedRate){
		this.setStockName(stockName);
		this.setGoodsNum(goodNum);
		this.setOccupiedRate(occupiedRate);
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public int getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
	}

	public double getOccupiedRate() {
		return occupiedRate;
	}

	public void setOccupiedRate(double occupiedRate) {
		this.occupiedRate = occupiedRate;
	}
}
