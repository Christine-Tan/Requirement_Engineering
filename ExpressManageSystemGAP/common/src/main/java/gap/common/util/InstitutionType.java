package gap.common.util;

public enum InstitutionType {
	BUSSINESS, CENTER;
	public static InstitutionType getInsType(String insNum) {
		int i = insNum.charAt(3) - '0';
		switch (i) {
		case 0:
			return BUSSINESS;
		case 1:
			return CENTER;
		default:
			return null;
		}
	}
}
