package gap.common.util;

public enum Gender {
	MALE, FEMALE;
	public static String toChinese(Gender gen) {
		switch (gen) {
		case MALE:
			return "男";
		case FEMALE:
			return "女";
		default:
			return null;
		}
	}

	public static Gender valueOfChinese(String str) {
		switch (str) {
		case "男":
			return MALE;
		case "女":
			return FEMALE;
		default:
			return null;
		}
	}
}
