package org.usfirst.frc.team2152.robot.subsystems;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls the SmartDashboard and Shuffleboard values and settings
 *
 */
public class Dashboard extends Subsystem {
	String ALLIANCE_PLATE_SIDE = null;
	String SWITCH_PLATE_SIDE = null;

	/**
	 * Gets our alliance's plate assignment for our switch and the scale and
	 * puts them on the SmartDashboard
	 */
	public void putPlateAssignment() {
		String ALLIANCE_PLATE = Robot.PLATE_ASSIGNMENT.substring(0, 1);
		String SWITCH_PLATE = Robot.PLATE_ASSIGNMENT.substring(1, 2);

		if (ALLIANCE_PLATE.equals("L")) {
			ALLIANCE_PLATE_SIDE = "Left";
		} else {
			ALLIANCE_PLATE_SIDE = "Right";
		}

		if (SWITCH_PLATE.equals("L")) {
			SWITCH_PLATE_SIDE = "Left";
		} else {
			SWITCH_PLATE_SIDE = "Right";
		}

		SmartDashboard.putString("Alliance Switch Plate", ALLIANCE_PLATE_SIDE);
		SmartDashboard.putString("Scale Plate", SWITCH_PLATE_SIDE);
	}

	/**
	 * Returns the assigned location of our alliance's plate on a given
	 * component
	 * 
	 * @param component
	 *            The plate assignment to get (eg. "Alliance Switch Plate")
	 * @return The assignment of component as a String(eg. "Left")
	 */
	public String getPlateAssignment(String component) {
		String s = null;
		if (component.equals("Alliance Switch Plate")) {
			s = SmartDashboard.getString(ALLIANCE_PLATE_SIDE, "");
		} else if (component.equals("Scale Plate")) {
			s = SmartDashboard.getString(SWITCH_PLATE_SIDE, "");
		}

		return s;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
