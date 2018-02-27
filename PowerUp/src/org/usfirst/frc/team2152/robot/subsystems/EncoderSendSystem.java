package org.usfirst.frc.team2152.robot.subsystems;

import org.usfirst.frc.team2152.robot.network.UDPClient;
import org.usfirst.frc.team2152.robot.utilities.ByteUtil;
import org.usfirst.frc.team2152.robot.utilities.Util;

public class EncoderSendSystem extends Thread {

	private boolean running = false; 
	private int hertz;

	private UDPClient out;
	int port;

	public EncoderSendSystem (int port, int hertz) {
		out = new UDPClient();
		this.hertz = hertz;
		this.port = port;
	}

	public void run() {
		running = true;
		long lastTime = System.currentTimeMillis();
		long delta = 0;
		while(running) {
			delta = System.currentTimeMillis() - lastTime;
			if(delta < 1000 / hertz)
				continue;

			System.out.println("Sending encoder data");
			System.out.println(System.nanoTime());

			int encoderL = 47;
			int encoderR = 123456;
			
			out.send( Util.concatBytes( ByteUtil.toByteArrayLE(encoderL)
									  , ByteUtil.toByteArrayLE(encoderR)
									  , ByteUtil.toByteArrayLE(System.nanoTime()))
					, "10.21.52.12", port);

			delta = 0;
			lastTime = System.currentTimeMillis();
		}
	}

	public void justStop() {
		running = false;
	}
}