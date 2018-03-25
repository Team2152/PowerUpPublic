package org.usfirst.frc.team2152.robot.commands;

import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.usfirst.frc.team2152.robot.network.UDPClient;
import org.usfirst.frc.team2152.robot.network.UDPReceiver;
import org.usfirst.frc.team2152.robot.network.UDPSender;
import org.usfirst.frc.team2152.robot.utilities.ByteUtil;

/**
 * sync the time
 */
public class SyncTime extends Thread {

	private final int confPortR;
	private final int timePortR;
	private final InetAddress addr;
	private final UDPClient client;

	private int numTrials;
	private int iteration = 0;
	private boolean done = false;

	private long time;
	private final int TIMEOUT = 1000;

	public SyncTime(UDPClient client, int confPortR, int timePortR, String addr, int numTrials) throws UnknownHostException {
		this.confPortR = confPortR;
		this.timePortR = timePortR;
    	this.addr = InetAddress.getByName(addr);
    	this.numTrials = numTrials;

    	this.client = client;
    }

    private void initialize() {
    }

    private void execute() {
    	//System.out.println("Sending 'bit'");
    	time = System.nanoTime();
    	client.send(new byte[] {1}, addr, confPortR);

    	//System.out.println("Waiting for conf 1");
    	try {
			client.receiveResponse(1, TIMEOUT);
		} catch (SocketTimeoutException e1) {
			System.out.println("Timed out (no confirmation received)");
			done = true;
			return;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("what");
			done = true;
			return;
		}

		byte[] boots = ByteUtil.toByteArrayLE(time);
		int[] bytes = ByteUtil.getUnsignedBytesFromLong(boots);
		//sender.sendMsg(boots, addr, remotePort);
		//System.out.println("Sending time pack");
		client.send(boots, addr, timePortR);
		/*
		System.out.printf("Sent time: %d; Byte array: {%d, %d, %d, %d, %d, %d, %d, %d}%n", 
				time, bytes[0], bytes[1], bytes[2], bytes[3], 
				bytes[4], bytes[5], bytes[6], bytes[7]);
				*/
				
    	//System.out.println("Waiting for conf 2");
		try {
			client.receiveResponse(1, TIMEOUT);
		} catch (SocketTimeoutException e1) {
			System.out.println("Timed out (no confirmation received 2)");
			done = true;
			return;
		}
    	//System.out.println("Received conf 2");

		iteration++;
		//System.out.printf("Iteration %d done%n", iteration);
		if(iteration >= numTrials)  {
			System.out.println("SyncTime success");
			done = true;
		}
    }
    
    private void end() {
    	System.out.println("Ending SyncTime");
    	//receiver.end();
    	//sock.close();
    }

	public void run() {
    	initialize();
    	while(!done) {
    		execute();
    	}
    	end();
    }
}
