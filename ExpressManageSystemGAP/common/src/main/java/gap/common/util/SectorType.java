package gap.common.util;

public enum SectorType {
	FLEX('0'), CAR('1'), TRAIN('2'), PLANE('3');

	// 航运，铁运，汽运，机动
	char c;
	
	private SectorType(char c){
		this.c = c;
	}
	
	public char getChar(){
		return this.c;
	}

	public static SectorType getSectorTypeByChinese(String name) {
		switch (name) {
		case "汽运区":
			return CAR;
		case "铁运区":
			return TRAIN;
		case "航运区":
			return PLANE;
		case "机动区":
			return FLEX;
		default:
			return null;
		}

	}

	public static SectorType getSectorType(String name) {
		switch (name) {
		case "CAR":
			return CAR;
		case "TRAIN":
			return TRAIN;
		case "PLANE":
			return PLANE;
		case "FLEX":
			return FLEX;
		default:
			return null;
		}

	}

	public static String getSectorId(String ins_id, SectorType name) {
		switch (name) {
		case CAR:
			return ins_id + "1";
		case TRAIN:
			return ins_id + "2";
		case PLANE:
			return ins_id + "3";
		case FLEX:
			return ins_id + "0";
		default:
			return null;
		}
	}
	
	public static String getName(char c){
		switch (c) {
		case '1':
			return "汽运区";
		case '2':
			return "铁运区";
		case '3':
			return "航运区";
		case '0':
			return "机动区";
		default:
			return null;
		}
	}
}
