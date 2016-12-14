package gap.client.bl.expressorder;

import gap.client.bl.strategy.CityManage;
import gap.client.vo.ExpressOrderVO;
import gap.common.util.Address;
import gap.common.util.CargoInfo;
import gap.common.util.ExpressType;
import gap.common.util.PeopleInfo;

import org.junit.Before;
import org.junit.Test;

public class PriceCalImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		ExpressOrder order = new ExpressOrder(
				new PriceCalImpl(new CityManage()));

		Address add1 = new Address("江苏省", "南京市", "玄武区");
		Address add2 = new Address("北京市", "北京市", "东城区");
		PeopleInfo sender = new PeopleInfo("杨雁飞", add1, "南京大学", "15520065137");
		PeopleInfo receiver = new PeopleInfo("张家盛", add2, "北京大学", "119");
		CargoInfo cargo = new CargoInfo(2, 2.5, 3.5, "测试");
		ExpressOrderVO vo = new ExpressOrderVO(sender, receiver,
				ExpressType.EXPRESS, 20.5, cargo, false, "0000000003",
				"0010001", null,null);

		ExpressOrderVO newVo = order.createOrder(vo);
		System.out.println(newVo.price);
	}

}
