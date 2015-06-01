package robot.dblot;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


public class DBLotCalculator {

	public static void main(String[] args) {
		final double DB_LOT = 1.37;
		final int PIP = 120;
		final int DUPLICATE_ORDER = 2;
		
		double lot = 0.01;
		int orderIndex = 24;
		
		List<Order> orders = new ArrayList<Order>();
		
		double currentLot = lot;
		double currentDbLot;
		for(int i = 0; i <= orderIndex; i++){
			if(i < DUPLICATE_ORDER)
				currentDbLot = 1;
			else
				currentDbLot = DB_LOT;
			
			currentLot *= currentDbLot;
			
			BigDecimal valueBigDecimal = new BigDecimal(currentLot);
			valueBigDecimal = valueBigDecimal.setScale(2, RoundingMode.HALF_DOWN);
			
			Order order = new Order(i, valueBigDecimal);
			orders.add(order);
		}
		
		double cent = 0;
		int pips = PIP*orderIndex;
		for(Order order : orders){
			cent += order.getLot().doubleValue()*pips;
			pips -= PIP;
		}
		BigDecimal centBigDecimal = new BigDecimal(cent);
		centBigDecimal = centBigDecimal.setScale(2, RoundingMode.HALF_DOWN);
		
		System.out.println("stop out: " + centBigDecimal + " | lot: " + lot + " | -pips: " + (PIP*(orders.size()-1)) + " | order index: " + (orders.size()-1));
	}
}
