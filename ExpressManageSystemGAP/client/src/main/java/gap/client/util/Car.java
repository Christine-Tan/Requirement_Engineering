package gap.client.util;

import gap.client.vo.CarVO;
import gap.common.po.CarPO;

public class Car {
	// 车辆编号
	private String car_id;
	// 车牌号
	private String car_num;
	// 服役时间
	private int serve_time;
	// 所在单位
	private String ins_id;

	public Car(String car_id, String car_num, int serve_time, String ins_id) {
		super();
		this.car_id = car_id;
		this.car_num = car_num;
		this.serve_time = serve_time;
		this.ins_id = ins_id;
	}

	public Car(CarPO po) {
		this.car_id = po.getCar_id();
		this.car_num = po.getCar_num();
		this.ins_id = po.getIns_id();
		this.serve_time = po.getServe_time();
	}

	public Car(CarVO vo) {
		this.car_id = vo.getCar_id();
		this.car_num = vo.getCar_num();
		this.ins_id = vo.getIns_id();
		this.serve_time = vo.getServe_time();
	}

	public CarPO toCarPO() {
		return new CarPO(car_id, car_num, ins_id, serve_time);
	}

	public CarVO toCarVO() {
		return new CarVO(car_id, car_num, ins_id, serve_time);
	}

	public String getCar_id() {
		return car_id;
	}

	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}

	public String getCar_num() {
		return car_num;
	}

	public void setCar_num(String car_num) {
		this.car_num = car_num;
	}

	public int getServe_time() {
		return serve_time;
	}

	public void setServe_time(int serve_time) {
		this.serve_time = serve_time;
	}

	public String getIns_id() {
		return ins_id;
	}

	public void setIns_id(String ins_id) {
		this.ins_id = ins_id;
	}

}
