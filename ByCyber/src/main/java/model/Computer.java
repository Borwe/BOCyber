package model;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.borwe.ByCyber.App;

import db.DataBaseAccess;

public class Computer {

	private String computerName;
	private String computerIp;
	private int computerId;
	
	public Computer(String computerName,String computerIP) {
		this.computerName=computerName;
		this.computerIp=computerIP;
	}
	
	public boolean saveComputerToDB() {
		Future<Boolean> success=App.dbExecutor.submit(()->{
			DataBaseAccess.getAccess().update("INSERT INTO COMPUTERS (COMPUTER_NAME,"
					+ "COMPUTER_IP) VALUES(?,?)", new Object[] {
						computerName,computerIp	
					});
			return true;
		});
		try {
			return success.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public String getComputerName() {
		return computerName;
	}
	
	public String getComputerIp() {
		return computerIp;
	}
	
	public int getComputerId() {
		return computerId;
	}

	@Override
	public String toString() {
		return "Computer [computerName=" + computerName + ", computerIp=" + computerIp + ", computerId=" + computerId
				+ "]";
	}
}
