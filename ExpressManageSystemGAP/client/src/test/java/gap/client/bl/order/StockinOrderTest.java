package gap.client.bl.order;

import static org.junit.Assert.fail;
import gap.client.datacontroller.NetModule;
import gap.client.vo.GoodsVO;
import gap.client.vo.StockinOrderVO;
import gap.common.util.SectorType;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StockinOrderTest {
	StockinOrder stockinOrder;
	List<GoodsVO> orders;

	@Before
	public void setUp() throws Exception {
		NetModule.connect();
		stockinOrder = new StockinOrder();
		orders = new ArrayList<GoodsVO>();
		//
		
		
		for(int i = 1;i<=9;i++){
			GoodsVO vo = new GoodsVO("000000000"+i, "A,A,"+i, SectorType.CAR, "2015-12-20", "00110011", "00110011", "江苏省南京市栖霞区");
			orders.add(vo);
		}
		GoodsVO vo10 = new GoodsVO("0000000010", "A,A,10", SectorType.CAR, "2015-12-20", "00110011", "00110011", "江苏省南京市栖霞区");
		GoodsVO vo11 = new GoodsVO("0000000011", "A,B,1", SectorType.CAR, "2015-12-20", "00110011", "00110011", "江苏省南京市栖霞区");
		GoodsVO vo12 = new GoodsVO("0000000012", "A,B,2", SectorType.CAR, "2015-12-20", "00110011", "00110011", "江苏省南京市栖霞区");
		orders.add(vo10);
		orders.add(vo11);
		orders.add(vo12);
		//
	}

	@Test
	public void testSave() {
		stockinOrder.save(new StockinOrderVO(orders, "2015-12-18", "00110012015121800001", "0011001"));
	}

//	@Test
//	public void testFind() {
//		fail("尚未实现");
//	}
//
//	@Test
//	public void testGetLocation() {
//		fail("尚未实现");
//	}

	@Test
	public void testGetArrivedOrderVO() {
		fail("尚未实现");
	}

	@Test
	public void testGetRequired() {
		stockinOrder.getRequired("2015-12-08", "2015-12-20", "0011001");
	}

	@Test
	public void testGetTotalNum() {
		stockinOrder.getTotalNum(stockinOrder.getRequired("2015-12-08", "2015-12-20", "0011001"));
	}

	@Test
	public void testGetNextId() {
		stockinOrder.getNextId("001100120151220");
	}

}
