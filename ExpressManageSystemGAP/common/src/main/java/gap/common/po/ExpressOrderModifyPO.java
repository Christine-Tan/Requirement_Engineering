package gap.common.po;

public class ExpressOrderModifyPO {
	String order_id;
	String currentIns_id, targetIns_id;
	boolean received, passed, isTransed;

	public ExpressOrderModifyPO(String order_id, String currentIns_id,
			String targetIns_id, boolean received, boolean passed,
			boolean isTransed) {
		super();
		this.order_id = order_id;
		this.currentIns_id = currentIns_id;
		this.targetIns_id = targetIns_id;
		this.received = received;
		this.passed = passed;
		this.isTransed = isTransed;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getCurrentIns_id() {
		return currentIns_id;
	}

	public void setCurrentIns_id(String currentIns_id) {
		this.currentIns_id = currentIns_id;
	}

	public String getTargetIns_id() {
		return targetIns_id;
	}

	public void setTargetIns_id(String targetIns_id) {
		this.targetIns_id = targetIns_id;
	}

	public boolean isReceived() {
		return received;
	}

	public void setReceived(boolean received) {
		this.received = received;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public boolean isTransed() {
		return isTransed;
	}

	public void setTransed(boolean isTransed) {
		this.isTransed = isTransed;
	}

}
