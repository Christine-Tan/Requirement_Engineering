package gap.server.data.transfare;

import gap.common.dataservice.transFareDataService.TransFareDataService;
import gap.common.po.TransFarePO;
import gap.server.data.transFareData.TransFareDataImpl;
import gap.server.initial.NetInitial;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TransFareTest {

	TransFareDataService transFareDataService;
	TransFarePO po;
	@Before
	public void setUp() throws Exception {
		NetInitial.initial();
		transFareDataService = TransFareDataImpl.getInstance();
		po = new TransFarePO(300, "2233223", "0010001004");
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testGetInstance() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetTransFare() throws Exception{
		List<TransFarePO> list = transFareDataService.getTransFare();
		
		for(TransFarePO po : list){
			System.out.println(po.getOrderID()+" "+po.getFare());
		}
		
	}

	@Test
	public void testDeleteTransFare() throws Exception{
		List<TransFarePO> list = new ArrayList<>();
		list.add(po);
		boolean result = transFareDataService.deleteTransFare(list);
		System.out.println(result);
	}

	@Test
	public void testAddTransFare() throws Exception{
		boolean result = transFareDataService.addTransFare(po);
		System.out.println(result);
	}

}
