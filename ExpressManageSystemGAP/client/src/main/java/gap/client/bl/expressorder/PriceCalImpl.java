package gap.client.bl.expressorder;

import gap.client.bl.strategy.CityManage;
import gap.client.blservice.expressorderblservice.PriceCal;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.ExpressOrderDataController;
import gap.client.util.City;
import gap.client.vo.ExpressOrderVO;
import gap.common.po.PricePO;

public class PriceCalImpl extends PriceCal {
	ExpressOrderDataController dataController;

	public PriceCalImpl() {
		dataController = ControllerFactory.getExpressOrderDataController();
	}

	public PriceCalImpl(CityManage city) {
		this();
		this.city = city;
	}

	@Override
	public double getPrice(ExpressOrderVO vo) {
		// TODO 自动生成的方法存根
		String sender_city = vo.sender_info.getAddress().getCity_name();
		String receiver_city = vo.receiver_info.getAddress().getCity_name();
		City sender = new City(city.getCity(sender_city)), receiver = new City(
				city.getCity(receiver_city));
		double distance = city.getDistance(sender, receiver);
		System.out.println("The distance from " + sender_city + " to "
				+ receiver_city + " is " + distance);
		PricePO priceInfo = dataController.findPrice(sender_city);
		double base = priceInfo.getBase();
		double price = 0;
		switch (vo.expressType) {
		case EXPRESS:
			price = distance / 1000 * priceInfo.getExpress() * base;
			break;
		case STANDARD:
			price = distance / 1000 * priceInfo.getStandard() * base;
			break;
		case ECONOMIC:
			price = distance / 1000 * priceInfo.getEconomic() * base;
			break;
		default:
			break;
		}
		return price * vo.cargoInfo.getWeight();
	}

	public static void main(String[] args) {

	}
}
