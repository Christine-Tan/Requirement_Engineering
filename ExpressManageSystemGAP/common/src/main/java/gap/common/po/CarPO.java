package gap.common.po;

import java.io.Serializable;

public class CarPO implements Serializable {
	// 车辆编号
	private String car_id;
	// 车牌号
	private String car_num;
	// 服役时间
	private int serve_time;
	// 所在单位
	private String ins_id;

	public CarPO(String car_id, String car_num, String ins_id, int serve_time) {
		super();
		this.car_id = car_id;
		this.car_num = car_num;
		this.setIns_id(ins_id);
		this.serve_time = serve_time;
	}

	public CarPO() {

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
