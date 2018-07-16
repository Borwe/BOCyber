package model;

public class Computer {

	private String computerName;
	private String computerIp;
	private int computerId;
	
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
