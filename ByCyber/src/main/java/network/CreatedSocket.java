package network;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class CreatedSocket {

	public static void searchDevicesInNetwork() {
		getBroadcastAddress().stream().forEach(s->{
			System.out.println(s);
		});
	}
	
	private static List<String> getBroadcastAddress() {
		List<String> broadCastIps=new ArrayList<>();
		try {
			Enumeration<NetworkInterface> networkInterfaces=
					NetworkInterface.getNetworkInterfaces();
			while(networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface=networkInterfaces.nextElement();
				
				
				if(networkInterface.isLoopback()) {
					continue;
				}
				
				for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) 
			    {
			        InetAddress broadcast = interfaceAddress.getBroadcast();
			        if (broadcast == null)
			            continue;

			        // Do something with the address.
			        broadCastIps.add(broadcast. getHostAddress());
			    }
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return broadCastIps;
	}
}
