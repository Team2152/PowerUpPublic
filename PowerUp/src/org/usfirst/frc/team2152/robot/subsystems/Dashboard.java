package org.usfirst.frc.team2152.robot.subsystems;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls the SmartDashboard and Shuffleboard values and settings
 *
 */
public class Dashboard extends Subsystem {
	// Variable Declaration
	String switchPlateSide = "";
	String scalePlateSide = "";

	/**
	 * Gets our alliance's plate assignment for our switch and the scale and
	 * puts them on the SmartDashboard
	 */
	public void putPlateAssignment() {
		/*
		 * Our plate on the opponent's Switch will always be on the same side as
		 * our plate on our own Switch, According to Figure 4-1 in the manual
		 */

		String switchPlate = String.valueOf(Robot.PLATE_ASSIGNMENT.charAt(0));
		String scalePlate = String.valueOf(Robot.PLATE_ASSIGNMENT.charAt(1));

		if (switchPlate.equalsIgnoreCase("L")) {
			switchPlateSide = "Left";
		} else if (switchPlate.equalsIgnoreCase("R")) {
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
		String s = "No component recognized";
		
		switch (component) {
		case "Switch Plates":
			s = SmartDashboard.getString(switchPlateSide, "No Data Recieved");
			break;
		case "Scale Plate":
			s = SmartDashboard.getString(scalePlateSide, "No Data Recieved");
			break;
		}
		
		return s;
	}

	@Override
	protected void initDefaultCommand() {

	}
}
