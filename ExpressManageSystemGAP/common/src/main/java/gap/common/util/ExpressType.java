package gap.common.util;

public enum ExpressType {
	ECONOMIC, STANDARD, EXPRESS;

	public static String getSectorByExpressType(ExpressType type) {
		switch(type){
		case ECONOMIC : return "汽运区";
		case STANDARD : return "铁运区";
		case EXPRESS : return "航运区";
		default: return null;
		}
	}
}
