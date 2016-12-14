package gap.common.netconfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RMIConfig {
	public static int RMI_port;
	public static String url;
	// = "rmi://127.0.0.1:" + RMI_port + "/";

	static {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(
					new File("RMIConfig.txt")));
			String IP = reader.readLine();
			RMI_port = Integer.parseInt(reader.readLine());
			url = "rmi://" + IP + ":" + RMI_port + "/";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
