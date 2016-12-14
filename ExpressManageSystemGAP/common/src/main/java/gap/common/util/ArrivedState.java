package gap.common.util;

public enum ArrivedState {
	COMPLETE, LOST, DAMAGE;
	public static ArrivedState getByChinese(String str) {
		switch (str) {
		case "完好":
			return COMPLETE;
		case "损坏":
			return DAMAGE;
		case "丢失":
			return LOST;
		default:
			return null;
		}
	}

	public static String toChinese(ArrivedState state) {
		switch (state) {
		case COMPLETE:
			return "完好";
		case LOST:
			return "丢失";
		case DAMAGE:
			return "损坏";
		default:
			return null;
		}
	}
}
