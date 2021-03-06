package org.usfirst.frc.team2152.robot.subsystems;

import org.usfirst.frc.team2152.robot.commands.Record;
import org.usfirst.frc.team2152.robot.commands.SendPositionBC;
import org.usfirst.frc.team2152.robot.commands.SendPositionBL;
import org.usfirst.frc.team2152.robot.commands.SendPositionBR;
import org.usfirst.frc.team2152.robot.commands.SendPositionRC;
import org.usfirst.frc.team2152.robot.commands.SendPositionRL;
import org.usfirst.frc.team2152.robot.commands.SendPositionRR;
import org.usfirst.frc.team2152.robot.commands.StopRecording;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls the SmartDashboard and Shuffleboard values and settings
 *
 */
public class Dashboard extends Subsystem {

	/**
	 * Gets our alliance's plate assignment for our switch and the scale and
	 * puts them on the SmartDashboard
	 */
	public void putUDP(boolean udpRunning) {
		SmartDashboard.putBoolean("UDP Connection", udpRunning);
	}
	
	public void putIMUData(double pitch, double yaw, double roll) {
		SmartDashboard.putNumber("Pitch", pitch);
		SmartDashboard.putNumber("Yaw", yaw);
		SmartDashboard.putNumber("Roll", roll);
	}
	
	public void putCubeVision(double angle, double distance, double rotation, double closest) {
		SmartDashboard.putNumber("Angle to Cube", angle);
		SmartDashboard.putNumber("Distance to Cube", distance);
		SmartDashboard.putNumber("Rotation of Cube", rotation);
		SmartDashboard.putNumber("Closest Cube", closest);
	}
	
	public void putPlateAssignment(String plateAssignment) {
		/*
		 * Our plate on the opponent's Switch will always be on the same side as
		 * our plate on our own Switch, According to Figure 4-1 in the manual
		 */
		String switchPlateSide = "";
		String scalePlateSide = "";
		String switchPlates = "";
		String scalePlate = "";
		if (plateAssignment.length() >= 3){
			switchPlates = String.valueOf(plateAssignment.charAt(0));
			scalePlate = String.valueOf(plateAssignment.charAt(1));
		}

		if (switchPlates.equalsIgnoreCase("L")) {
			switchPlateSide = "Left";
		} else if (switchPlates.equalsIgnoreCase("R")) {
			switchPlateSide = "Right";
		}

		if (scalePlate.equalsIgnoreCase("L")) {
			scalePlateSide = "Left";
		} else if (scalePlate.equalsIgnoreCase("R")) {
			scalePlateSide = "Right";
		}

		SmartDashboard.putString("Switch Plates", switchPlateSide);
		SmartDashboard.putString("Scale Plate", scalePlateSide);
	}

	/**
	 * Returns the assigned location of our alliance's plate on a given
	 * component
	 * 
	 * @param component
	 *            The plate assignment to get (eg. "Switch Plates")
	 * @return The assignment of component as a String(eg. "Left")
	 */
	public String getPlateAssignment(String component) {
		switch (component) {
		case "Switch Plates":
			return SmartDashboard.getString("Switch Plates", "No Data Recieved");
		case "Scale Plate":
			return SmartDashboard.getString("Scale Plate", "No Data Recieved");
		default:
			return "Component Not Recognized";
		
		}
	}

	/**
	 * Displays encoder data on the Dashboard
	 * 
	 * @param encoderL
	 *            Left encoder value
	 * @param encoderR
	 *            Right encoder value
	 */
	public void putEncoderData(double encoderL, double encoderR) {
		SmartDashboard.putNumber("L Pos", encoderL);
		SmartDashboard.putNumber("R Pos", encoderR);
	}

	/**
	 * Gets the encoder data from the Dashboard
	 * 
	 * @param encoder
	 *            Defines the encoder to grab data from
	 * @return The data from the encoder
	 */
	public double getEncoderData(String encoder) {
		double d = 0;
		switch (encoder) {
		case "Left":
			d = SmartDashboard.getNumber("L Pos", 0);
			break;
		case "Right":
			d = SmartDashboard.getNumber("R Pos", 0);
			break;
		}
		return d;
	}

	/**
	 * Places toggles for reseting encoder data on the Dashboard
	 */
	public void putEncoderReset() {
		SmartDashboard.putBoolean("Reset Left Encoder", false);
		SmartDashboard.putBoolean("Reset Right Encoder", false);
		SmartDashboard.putBoolean("Reset Both Encoders", false);
	}

	/**
	 * Get status from encoder reset Toggles
	 * 
	 * @param encoder
	 *            Toggle to check
	 * @return value of the Toggle
	 */
	public boolean getEncoderReset(String encoder) {
		boolean b = false;
		switch (encoder) {
		case "Left":
			b = SmartDashboard.getBoolean("Reset Left Encoder", false);
			break;
		case "Right":
			b = SmartDashboard.getBoolean("Reset Right Encoder", false);
			break;
		case "Both":
			b = SmartDashboard.getBoolean("Reset Both Encoders", false);
			break;
		}
		return b;
	}

	/**
	 * Checks cube manipulator IR limit switch status and displays on the
	 * Dashboard
	 * 
	 * @param leftIn
	 *            LeftIntake Sensor
	 * @param rightIn
	 *            RightIntake Sensor
	 * @param leftOut
	 *            LeftOuttake Sensor
	 * @param rightOut
	 *            RightOuttake Sensor
	 */
	public void putCubeStatus(boolean intake, boolean leftOut, boolean rightOut) {
		SmartDashboard.putBoolean("Cube Detect - Intake", intake);
		SmartDashboard.putBoolean("Cube Detect - OuterLeft", leftOut);
		SmartDashboard.putBoolean("Cube Detect - OuterRight", rightOut);
	}

	/**
	 * Displays the cube solenoid state on the Dashboard
	 * 
	 * @param solenoidState
	 */
	public void putCubeSolenoid(boolean solenoidState) {
		SmartDashboard.putBoolean("Cube Solenoid Open", solenoidState);
	}
	
	/**
	 * Shows the drivers if the cube manipulator hit one of its limit switches
	 * @param highLimit Upper Limit Switch
	 * @param lowLimit Lower Limit Switch
	 */
	public void putCubeMoveStatus(boolean highLimit, boolean lowLimit){
		SmartDashboard.putBoolean("Cube High Limit", highLimit);
		SmartDashboard.putBoolean("Cube Low Limit", lowLimit);
	}

	/**
	 * Puts vision data onto the Dashboard
	 * 
	 * @param cubeAngle
	 *            Angle to cube
	 * @param cubeDistance
	 *            Distance to cube
	 */
	public void putVisionData(double cubeAngle, double cubeDistance) {
		SmartDashboard.putNumber("Angle to Cube", cubeAngle);
		SmartDashboard.putNumber("Distance to Cube", cubeDistance);
	}

	/**
	 * Returns vision data from the Dashboard
	 * 
	 * @param data
	 *            Vision/Angle states what data to grab
	 * @return The specified data
	 */
	public double getVisionData(String data) {
		double d = 0;
		switch (data) {
		case "Angle":
			d = SmartDashboard.getNumber("Angle to Cube", 0);
			break;
		case "Distance":
			d = SmartDashboard.getNumber("Distance to Cube", 0);
			break;
		}
		return d;
	}

	
	public void putRecording() {
		SmartDashboard.putData("EnableRecording", new Record());
		SmartDashboard.putData("DisableRecording", new StopRecording());
	}
	
	public void putPositions(){
		SmartDashboard.putData("Send Pos: BL", new SendPositionBL());
		SmartDashboard.putData("Send Pos: BC", new SendPositionBC());
		SmartDashboard.putData("Send Pos: BR", new SendPositionBR());
		SmartDashboard.putData("Send Pos: RL", new SendPositionRL());
		SmartDashboard.putData("Send Pos: RC", new SendPositionRC());
		SmartDashboard.putData("Send Pos: RR", new SendPositionRR());
	}
	
	public void putElevatorStatus(boolean maxHeight, boolean minHeight) {
		SmartDashboard.putBoolean("Elevator Max Height", maxHeight);
		SmartDashboard.putBoolean("Elevator Min Height", minHeight);
	}

	@Override
	protected void initDefaultCommand() {
		
	}
}
