package gap.mockobject;

import java.util.ArrayList;
import java.util.List;



import gap.client.bl.stockinorder.LocationCal;
import gap.client.vo.GoodsVO;
import gap.common.po.SectorFlexPO;
import gap.common.util.SectorType;

public class MockLocationCal extends LocationCal{
	
	int rows;
	int shelves;
	int units;
	ArrayList <GoodsVO> list;

	@Override
	public String setLocation(GoodsVO vo) {
		int size = list.size()+1;
		String location = size+"";
		vo.setLocation(location);
		// TODO Auto-generated method stub
		/*String location = "";
		int size = list.size()+1;
		char row = (char)(size/(shelves*units)+'A');
		size = size%(shelves*units);
		char shelf = (char)(size/units+'1');
		char unit = (char)(size%rows+'0');
		location +=row+shelf+unit;*/
		
		return location;
	}

	@Override

	public String getLocation(GoodsVO vo) {
		// TODO Auto-generated method stub
		list.add(vo);
		setLocation(vo);
		return vo.getLocation();
	}

}
