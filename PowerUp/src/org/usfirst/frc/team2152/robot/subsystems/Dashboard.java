package org.usfirst.frc.team2152.robot.subsystems;

import org.usfirst.frc.team2152.robot.Robot;

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
	public void putPlateAssignment() {
		/*
		 * Our plate on the opponent's Switch will always be on the same side as
		 * our plate on our own Switch, According to Figure 4-1 in the manual
		 */
		String switchPlateSide = "";
		String scalePlateSide = "";
		String switchPlates = String.valueOf(Robot.PLATE_ASSIGNMENT.charAt(0));
		String scalePlate = String.valueOf(Robot.PLATE_ASSIGNMENT.charAt(1));

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
	 * @param component The plate assignment to get (eg. "Switch Plates")
	 * @return The assignment of component as a String(eg. "Left")
	 */
	public String getPlateAssignment(String component) {
		String s = "No data recieved";
		switch (component) {
		case "Switch Plates":
			s = SmartDashboard.getString("Switch Plates", "No data recieved");
			break;
		case "Scale Plate":
			s = SmartDashboard.getString("Scale Plate", "No data recieved");
			break;
		}
		return s;
	}
	
	/**
	 * Displays encoder data on the Dashboard
	 * @param encoderL Left encoder value
	 * @param encoderR Right encoder value
	 */
	public void putEncoderData(double encoderL, double encoderR) {
		SmartDashboard.putNumber("Left Encoder", encoderL);
		SmartDashboard.putNumber("Right Encoder", encoderR);
	}
	
	/**
	 * Gets the encoder data from the Dashboard
	 * @param encoder Defines the encoder to grab data from
	 * @return The data from the encoder
	 */
	public double getEncoderData(String encoder) {
		double d = 0;
		switch (encoder) {
		case "Left":
			d = SmartDashboard.getNumber("Left Encoder", 0);
			break;
		case "Right":
			d = SmartDashboard.getNumber("Right Encoder", 0);
			break;
		}
		return d;
	}
	
	public void putEncoderReset(){
		SmartDashboard.putBoolean("Reset Left Encoder", false);
		SmartDashboard.putBoolean("Reset Right Encoder", false);
		SmartDashboard.putBoolean("Reset Both Encoders", false);
	}
	
	public boolean getEncoderReset(String encoder){
		boolean b = false;
		switch (encoder){
		case "Left":
			b = SmartDashboard.getBoolean("Reset Left Encoder", false);
			break;
		case "Right":
			b =	SmartDashboard.getBoolean("Reset Right Encoder", false);
			break;
		case "Both":
			b = SmartDashboard.getBoolean("Reset Both Encoders", false);
			break;
		}
		return b;
	}
	
	/**
	 * Shows cube possession on the Dashboard
	 * @param status Cube possession data
	 */
	public void putCubeStatus(boolean status) {
		SmartDashboard.putBoolean("Cube Possession", status);
	}
	
	/**
	 * Returns cube possession from the Dashboard
	 * @return Cube possession data
	 */
	public boolean getCubeStatus() {
		return SmartDashboard.getBoolean("Cube Possession", false);
	}
	
	/**
	 * Puts vision data onto the Dashboard
	 * @param cubeAngle Angle to cube
	 * @param cubeDistance Distance to cube
	 */
	public void putVisionData(double cubeAngle, double cubeDistance) {
		SmartDashboard.putNumber("Angle to Cube", cubeAngle);
		SmartDashboard.putNumber("Distance to Cube", cubeDistance);
	}
	
	/**
	 * Returns vision data from the Dashboard
	 * @param data Vision/Angle states what data to grab
	 * @return The specified data
	 */
	public double getVisionData(String data) {
		double d = 0;
		switch (data){
		case "Angle":
			d = SmartDashboard.getNumber("Angle to Cube", 0);
			break;
		case "Distance":
			d = SmartDashboard.getNumber("Distance to Cube", 0);
			break;
		}
		return d;
	}
	
	public void putVideoRecording() {
		//SmartDashboard.putData(new startRecording());  Needs command to be written
		//SmartDashboard.putData(new stopRecording());  Needs command to be written

	}

	@Override
	protected void initDefaultCommand() {

	}
}
