package org.usfirst.frc.team2152.robot.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.usfirst.frc.team2152.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OdroidsCameraSettings extends Thread {

	private InetAddress odroid1Addr, odroid2Addr, odroid3Addr;

	/*
	private String odroid1IP = "10.21.52.11";
	private String odroid2IP = "10.21.52.12";
	private String odroid3IP = "10.21.52.13";

	private int odroid1Port = 5810;
	private int odroid2Port = 5810;
	private int odroid3Port = 5810;
	*/

	private UDPSender sender = new UDPSender();

	private boolean pegCamDark = true;
	private boolean boilerCamDark = false;
	private boolean ropeCamDark = false;
	private boolean intakeCamDark = false;

	private boolean record = false;

	public enum CAMERAS {
		PEG, BOILER, ROPE, INTAKE
	};

	private final int TIMEOUT_MILIS = 100;

	private boolean kill = false;

	public OdroidsCameraSettings() {
		try {
			odroid1Addr = InetAddress.getByName(RobotMap.ODROID_1_IP);
			odroid2Addr = InetAddress.getByName(RobotMap.ODROID_2_IP);
			odroid3Addr = InetAddress.getByName(RobotMap.ODROID_3_IP);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	//Sets Peg camera to dark light settings for reflective tape
	public void setToAutoMode() {
		//Robot.LEDsubsystem.setVoltage("on");
		pegCamDark = true;
		boilerCamDark = false;
		ropeCamDark = false;
		intakeCamDark = false;
	}

	//Very important:  if you change this function incorrectly you could break all tape detection features.
    public void notifyOdroids() {
    	//TEAM SMASH:  The order of the string matters!
    	//CS; PEG ; BOILER ; ROPE ; INTAKE
    	String msg = ("CS;" +
    			(pegCamDark ? "1;" : "0;") +
                (boilerCamDark ? "1;" : "0;") +
                (ropeCamDark ? "1;" : "0;") +
                (intakeCamDark ? "1;" : "0;")
        );
    	sender.sendMsg(msg, odroid1Addr, RobotMap.UDP_ODROID_CAM_SETTINGS_PORT_R);
    	sender.sendMsg(msg, odroid2Addr, RobotMap.UDP_ODROID_CAM_SETTINGS_PORT_R);
    }
    
    public void updateRecordStatus() {
    	String msg = ("Rec;" + (record ? "1;" : "0;") + System.currentTimeMillis() + ";");
    	sender.sendMsg(msg, odroid3Addr, RobotMap.UDP_ODROID_CAM_SETTINGS_PORT_R);
    }
    
    public void startRecording() {
    	String msg = ("1");
    	sender.sendMsg(msg, odroid3Addr, RobotMap.UDP_ODROID_CAM_SETTINGS_PORT_R);
    }
    
    public void stopRecording() {
    	String msg = ("0");
    	sender.sendMsg(msg, odroid3Addr, RobotMap.UDP_ODROID_CAM_SETTINGS_PORT_R);
    }
    
    public void sendGameData(String data) {
    	sender.sendMsg(data, odroid1Addr, RobotMap.UDP_ODROID_CAM_SETTINGS_PORT_R);
    }

	public void setToDisabledMode() {
		pegCamDark = true;
		boilerCamDark = false;
		ropeCamDark = false;
		intakeCamDark = false;
		try {
			Alliance alliance = DriverStation.getInstance().getAlliance();
			SmartDashboard.putString("alliance", alliance.toString());
			if (alliance == Alliance.Blue) {
				//Robot.LEDsubsystem.setVoltage("load blue");
				SmartDashboard.putString("alliance2", alliance.toString());
			} else if (alliance == Alliance.Red) {
				//Robot.LEDsubsystem.setVoltage("load red");
				SmartDashboard.putString("alliance2", alliance.toString());
			} else {
				//Robot.LEDsubsystem.setVoltage("load");
			}
		} catch (Exception e) {
			//Robot.LEDsubsystem.setVoltage("load");
			System.out.println(e);
		}
	}

	//Makes all cameras light / normal lighting
	//Turns off LED Ring
	public void setToTeleMode() {
		pegCamDark = false;
		boilerCamDark = false;
		ropeCamDark = false;
		intakeCamDark = false;
		//Robot.LEDsubsystem.setVoltage("off");
	}

	public void shouldRecord(boolean record) {
		this.record = record;
	}


	public void killThread() {
		kill = true;
	}

	public void run() {
		while (!kill) {
			//notifyOdroids();
			//updateRecordStatus(); 
			try {
				Thread.sleep(TIMEOUT_MILIS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
