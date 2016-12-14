package gap.client.blcontroller;

import gap.client.bl.strategy.PriceManage;
import gap.client.vo.PriceVO;
import gap.common.util.ResultMessage;

import java.util.List;

public class PriceController {
	private static PriceManage priceManage = new PriceManage();

	public static List<PriceVO> getAll() {
		return priceManage.getAll();
	}

	public static PriceVO getPrice(String city) {
		return priceManage.getPrice(city);
	}

	public static void modifyPrice(PriceVO vo) {
		priceManage.modifyPrice(vo);
	}

	public static void addPrice(PriceVO vo) {
		priceManage.addPrice(vo);
	}

	public static ResultMessage flush() {
		return priceManage.flush();

	}
}
