package gap.client.util;

public class Distance {
	private static final double EARTH_RADIUS = 6378.137;

	private double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 根据两点间经纬度坐标值，计算两点间距离，单位为米
	 * 
	 * @param latitude1
	 * @param longitude1
	 * @param latitude2
	 * @param longitude2
	 * @return
	 */
	public double distanceCal(double latitude1, double longitude1,
			double latitude2, double longitude2) {
		double a = rad(latitude1) - rad(latitude2);
		double b = rad(longitude1) - rad(longitude2);
		double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(rad(latitude1)) * Math.cos(rad(latitude2))
				* Math.pow(Math.sin(b / 2), 2)));
		distance = Math.round(distance * EARTH_RADIUS * 10000) / 10000;
		return distance;
	}
}
