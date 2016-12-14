package gap.server.ui.tools;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class IPGetter {
	public static String getIP(){
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String ip=addr.getHostAddress().toString();
		return ip;
	}
}
