package gap.client.util;

public enum Transport {
	CAR,PLANE,TRAIN;
	
	public static Transport getTransportByName(String name){
		switch(name){
		case "汽车运输": return CAR;
		case "铁路运输": return TRAIN;
		case "航空运输": return PLANE;
		default: return null;
		}
	}
}
