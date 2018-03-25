package org.usfirst.frc.team2152.robot.subsystems;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.RobotMap;
import org.usfirst.frc.team2152.robot.network.UDPClient;
import org.usfirst.frc.team2152.robot.utilities.ByteUtil;
import org.usfirst.frc.team2152.robot.utilities.Util;

/**
 * Continuously sends cumulative left and right encoder ticks to lidar odroid with timestamps
 * @author David
 */
public class EncoderSendSystem extends Thread {

	private boolean running = false; 
	private int hertz;

	private UDPClient out;
	int remotePort;

	public EncoderSendSystem (int remotePort, int hertz) {
		out = new UDPClient();
		this.hertz = hertz;
		this.remotePort = remotePort;
	}

	public void run() {
		running = true;
		// long lastTime = System.currentTimeMillis();
		// long delta = 0;
		while(running) {
			// delta = System.currentTimeMillis() - lastTime;
			// if(delta < 1000 / hertz)
			// 	continue;

			//System.out.println("Sending encoder data");
			//System.out.println(System.nanoTime());

			int encoderL = Robot.driveTrainSubsystem.getEncoderRTicksCumulative();
			int encoderR = Robot.driveTrainSubsystem.getEncoderLTicksCumulative();
			
			out.send( Util.concatBytes( ByteUtil.toByteArrayLE(encoderL)
									  , ByteUtil.toByteArrayLE(encoderR)
									  , ByteUtil.toByteArrayLE(System.nanoTime()))
					, RobotMap.LIDAR_ODROID_IP, remotePort);

			// delta = 0;
			// lastTime = System.currentTimeMillis();
			try {
				Thread.sleep(1000 / hertz);
				//System.out.println("TEST LOOP FOR ENCTIME MSGS");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void justStop() {
		running = false;
	}
}
