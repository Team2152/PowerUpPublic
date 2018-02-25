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

	private final int remotePort;
	private final InetAddress addr;
	private UDPReceiver receiver;
	private UDPSender sender;
	private final UDPClient client;

	private Object confLock = new Object();
	private volatile boolean timeout = false;

	private int numTrials;
	private int iteration = 0;
	private boolean done = false;

	private long time;
	private final int TIMEOUT = 1000;

	/*
    public SyncTime(UDPReceiver receiver, UDPSender sender, int remotePort, String addr, int numTrials) throws UnknownHostException {
    	this.remotePort = remotePort;
    	this.addr = InetAddress.getByName(addr);
    	this.numTrials = numTrials;
    	this.receiver = receiver;
    	this.sender = sender;
    	this.receiver.setListener(this::confirm);
    }
    */

	public SyncTime(UDPClient client, int remotePort, String addr, int numTrials) throws UnknownHostException {
    	this.remotePort = remotePort;
    	this.addr = InetAddress.getByName(addr);
    	this.numTrials = numTrials;

    	this.client = client;

    	//this.receiver.setListener(this::confirm);
    }

    private void initialize() {
     	//System.out.println("Initializing SyncTime");
    	/*
     	if(!receiver.isRunning()) {
			receiver.start();
     	}
     	*/
    }

    private void execute() {
    	timeout = true;
    	//System.out.println("Sending 'bit'");
    	time = System.nanoTime();
    	//sender.sendMsg(new byte[] {1}, addr, remotePort);
    	client.send(new byte[] {1}, addr, remotePort);

    	try {
			client.receiveResponse(1, TIMEOUT);
		} catch (SocketTimeoutException e1) {
			System.out.println("Timed out (no confirmation received)");
			done = true;
			return;

		}

    	/*
    	synchronized(confLock) {
			try {
				confLock.wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
    	}
		if(timeout) {
			System.out.println("Timed out (no confirmation received)");
			done = true;
			return;
		}
		timeout = true;
		*/

		byte[] boots = ByteUtil.toByteArrayLE(time);
		int[] bytes = ByteUtil.getUnsignedBytesFromLong(boots);
		//sender.sendMsg(boots, addr, remotePort);
		client.send(boots, addr, remotePort);
		/*System.out.printf("Sent time: %d; Byte array: {%d, %d, %d, %d, %d, %d, %d, %d}%n", 
				time, bytes[0], bytes[1], bytes[2], bytes[3], 
				bytes[4], bytes[5], bytes[6], bytes[7]);
				*/

		try {
			client.receiveResponse(1, TIMEOUT);
		} catch (SocketTimeoutException e1) {
			System.out.println("Timed out (no confirmation received 2)");
			done = true;
			return;
		}
		/*
		if(timeout) {
			synchronized(confLock) {
				try {
					confLock.wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
			if(timeout) {
				System.out.println("Timed out (no confirmation received 2)");
				done = true;
				return;
			}
		}
		*/

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

    public void confirm(byte[] data) {
    	//System.out.println("Confirming");
    	synchronized(confLock) {
			confLock.notify();
    	}
    	timeout = false;
    }

	public void run() {
    	initialize();
    	while(!done) {
    		execute();
    	}
    	end();
    }
}