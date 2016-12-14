package gap.common.util;

public enum PaymentType {
	INVENTORY {
		public String getEntry() {
			return "人员工资";
		}
	},

	CENTERCLERK {
		public String getEntry() {
			return "人员工资";
		}
	},

	BUSSINESSCLERK {
		public String getEntry() {
			return "人员工资";
		}
	},

	DELIVERY {
		public String getEntry() {
			return "人员工资";
		}
	},

	RENT {
		public String getEntry() {
			return "租金";
		}
	},

	TRANSFARE {
		public String getEntry() {
			return "运费";
		}
	},

	REWARD {
		public String getEntry() {
			return "奖金";
		}
	};

	abstract public String getEntry();
}
