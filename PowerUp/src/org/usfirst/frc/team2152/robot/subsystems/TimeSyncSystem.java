package org.usfirst.frc.team2152.robot.subsystems;

import java.net.DatagramSocket;
import java.net.UnknownHostException;

import org.usfirst.frc.team2152.robot.RobotMap;
import org.usfirst.frc.team2152.robot.commands.SyncTime;
import org.usfirst.frc.team2152.robot.network.UDPClient;
import org.usfirst.frc.team2152.robot.network.UDPReceiver;
import org.usfirst.frc.team2152.robot.network.UDPSender;

public class TimeSyncSystem {

	public UDPSender syncSender;
	public UDPReceiver syncReceiver;
	private UDPClient syncClient;

	private int confPort;
	private int timePort;

	private DatagramSocket syncSock = null;

	public TimeSyncSystem(int confPort, int timePort) {
		/*
		try {
			syncSock = new DatagramSocket(22221);
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		*/
		//syncReceiver = new UDPReceiver(syncSock);
		//syncSender = new UDPSender(syncSock);
		syncClient = new UDPClient();
		this.confPort = confPort;
		this.timePort = timePort;
	}

	public void startTimeSync(int numTrials) {
		System.out.println("Command 3 received: Starting SyncTime");
		try {
			new SyncTime(syncClient, confPort, timePort, RobotMap.LIDAR_ODROID_IP, numTrials).start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}
}
