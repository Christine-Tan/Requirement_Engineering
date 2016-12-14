package gap.client.vo;

import gap.common.po.GoodsPO;
import gap.common.util.SectorType;

import java.util.ArrayList;
import java.util.List;

public class GoodsVO {
	// 快递编号(10位0~9数字)
	private String expressorder_id;

	// 仓库中存放位置
	private String location;

	// 所在分区
	private SectorType sector;

	// 入库日期
	private String date;

	// 所在分区的id;
	private String sector_id;

	// 若在机动区分配到的分区的id
	private String belong_sector_id;

	// 最终目的地
	private String destination;

	public GoodsVO() {

	}

	public GoodsVO(GoodsPO po) {
		this.expressorder_id = po.getExpressorder_id();
		this.location = po.getLocation();
		this.sector = po.getSector();
		this.date = po.getDate();
		this.sector_id = po.getSector_id();
		this.belong_sector_id = po.getBelong_sector_id();
		this.destination = po.getDestination();
	}

	public GoodsVO(String expressorder_id, String location, SectorType sector,
			String date, String sector_id, String belong_sector_id,
			String destination) {
		super();
		this.expressorder_id = expressorder_id;
		this.location = location;
		this.sector = sector;
		this.date = date;
		this.sector_id = sector_id;
		this.belong_sector_id = belong_sector_id;
		this.destination = destination;
	}

	public String getExpressorder_id() {
		return expressorder_id;
	}

	public void setExpressorder_id(String expressorder_id) {
		this.expressorder_id = expressorder_id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public SectorType getSector() {
		return sector;
	}

	public SectorType getSectorType(char c) {
		switch (c) {
		case '0':
			return SectorType.FLEX;
		case '1':
			return SectorType.CAR;
		case '2':
			return SectorType.TRAIN;
		case '3':
			return SectorType.PLANE;
		default:
			return null;
		}
	}

	public void setSector(SectorType sector) {
		this.sector = sector;
	}

	public String getSector_id() {
		return sector_id;
	}

	public void setSector_id(String sector_id) {
		this.sector_id = sector_id;
	}

	public String getBelong_sector_id() {
		return belong_sector_id;
	}

	public void setBelong_sector_id(String belong_sector_id) {
		this.belong_sector_id = belong_sector_id;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public GoodsPO toPO() {
		GoodsPO po = new GoodsPO(this.expressorder_id, this.location,
				this.sector, this.date, this.sector_id, this.belong_sector_id,
				this.destination);
		return po;
	}

	public static List<GoodsVO> toVOList(List<GoodsPO> list) {
		// TODO Auto-generated method stub
		List<GoodsVO> voList = new ArrayList<GoodsVO>();
		if(list!=null){
			for(int i = 0;i<list.size();i++){
				GoodsVO vo = new GoodsVO(list.get(i));
				voList.add(vo);
			}
		}
		
		return voList;
	}
}
