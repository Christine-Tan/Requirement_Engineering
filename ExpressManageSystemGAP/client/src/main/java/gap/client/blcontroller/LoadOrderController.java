package gap.client.blcontroller;

import gap.client.bl.order.LoadOrder;
import gap.client.bl.strategy.CityManage;
import gap.client.util.City;
import gap.client.util.LocalInfo;
import gap.client.vo.LoadOrderVO;
import gap.common.util.ResultMessage;

import java.util.List;

public class LoadOrderController {
	private static LoadOrder loadOrder = new LoadOrder();

	public static ResultMessage save(LoadOrderVO loadOrderVO) {
		return loadOrder.save(loadOrderVO);
	}

	public static double setPrice(LoadOrderVO loadorderVO) {
		CityManage cityManage = new CityManage();
		City departurCity = new City(cityManage.getCity(InstitutionController
				.findById(loadorderVO.departureins_id).getInsCity()));
		City targetCity = new City(cityManage.getCity(InstitutionController
				.findById(loadorderVO.targetins_id).getInsCity()));
		double distance = cityManage.getDistance(departurCity, targetCity);
		double price = distance * 2;
		loadorderVO.price = price;
		return price;
	}

	public static List<LoadOrderVO> getArrivingLoadOrder() {
		return loadOrder.getArrivingLoadOrder(LocalInfo.ins_id);
	}

}
