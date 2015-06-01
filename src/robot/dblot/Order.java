package robot.dblot;

import java.math.BigDecimal;

public class Order {
	private int orderNo;
	private BigDecimal lot;
	private int pip;
	private double cent;

	Order(int orderNo, BigDecimal lot){
		this.orderNo = orderNo;
		this.lot = lot;
	}
	
	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getLot() {
		return lot;
	}

	public void setLot(BigDecimal lot) {
		this.lot = lot;
	}

	public int getPip() {
		return pip;
	}

	public void setPip(int pip) {
		this.pip = pip;
	}

	public double getCent() {
		return cent;
	}

	public void setCent(double cent) {
		this.cent = cent;
	}
}
