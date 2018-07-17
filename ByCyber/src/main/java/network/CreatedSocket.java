package network;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class CreatedSocket {

	public static void searchDevicesInNetwork() {
		try {
			Enumeration<NetworkInterface> networkInterfaces=
					NetworkInterface.getNetworkInterfaces();
			while(networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface=networkInterfaces.nextElement();
				
				
				if(networkInterface.isLoopback()) {
					continue;
				}
				
				/*for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) 
			    {
			        InetAddress broadcast = interfaceAddress.getBroadcast();
			        if (broadcast == null)
			            continue;

			        // Do something with the address.
			    }*/
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
