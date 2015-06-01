package robot.dblot;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class DBLotCalculator2 {
	final static double DB_LOT = 1.37;
	final static int FIX_PIP = 120;
	final static int DUPLICATE_ORDER = 2;
	
	final static double INPUT_DEPOSIT = 40000;//240000;//377800;//440300;			//FIXME HERE INPUT your deposit in cent
	final static int INPUT_MAXIMUM_PIP = 4000;										//FIXME HERE INPUT your maximum pips
//	final static double INPUT_LOT = 0.2;
	
	public static void main(String[] args) {
		double lot = 0.01;
		while(lot < 0.21){
			List<Order> orders = new ArrayList<Order>();
			int currentPip = 0;
			double currentLot = lot;
			while(currentPip < INPUT_MAXIMUM_PIP){
				double currentDbLot;
				
				if(orders.size() < DUPLICATE_ORDER)
					currentDbLot = 1;
				else
					currentDbLot = DB_LOT;
				
				currentLot *= currentDbLot;
				
				BigDecimal currentLotBigDecimal = new BigDecimal(currentLot);
				currentLotBigDecimal = currentLotBigDecimal.setScale(2, RoundingMode.HALF_DOWN);
			
				orders.add(new Order(orders.size(), currentLotBigDecimal));
				currentPip = FIX_PIP*(orders.size()-1);
				double cent = DBLotCalculator2.cent(orders);
				if(cent > INPUT_DEPOSIT){
					DecimalFormat format = new DecimalFormat("0.00");
					BigDecimal centBigDecimal = new BigDecimal(cent);
					centBigDecimal = centBigDecimal.setScale(2, RoundingMode.HALF_DOWN);
					System.out.println("stop out: " + centBigDecimal +" | lot: "+format.format(lot)+" | -pips: "+currentPip+" | order index: "+(orders.size()-1));
					break;
				}
			}
			
			lot += 0.01;
		}
	}
	
	private static double cent(List<Order> orders){
		int pips = FIX_PIP*(orders.size()-1);
		double cent = 0;
		for(Order order : orders){
//			System.out.println(order.getLot());
			cent += order.getLot().doubleValue()*pips;
			pips -= FIX_PIP;
		}
//		System.out.println(cent);
//		System.out.println("==========================================");
		return cent;
	}
}
