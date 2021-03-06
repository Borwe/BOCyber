package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import settings.Settings;

public class CreatedSocket {

	public static void searchDevicesInNetwork() {
		System.out.println("Listening");
		try {
			DatagramSocket ds=new DatagramSocket(Integer.parseInt(Settings.getPortNumber()));
			byte buf[]=new byte[1000*60];
			DatagramPacket dp=new DatagramPacket(buf, buf.length);
			
			ds.setSoTimeout((int)(Double.parseDouble(Settings.getBackgroundDelay())*1000*60));
			ds.receive(dp);
			
			System.out.println("DATA: "+new String(dp.getData()));
			ds.close();
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
