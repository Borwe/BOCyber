package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class CreatedSocketClient {

	public static void broadcastToByCyber() {
		try {
			DatagramSocket ds=new DatagramSocket();
			String test="Fucking testing bitch";
			
			DatagramPacket dp=new DatagramPacket(test.getBytes(), test.getBytes().length,
					Inet4Address.getByName(getBroadcastAddress().get(0)), 5555);
			
			ds.send(dp);
			ds.close();
			
			System.out.println("LINK: "+getBroadcastAddress().get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
