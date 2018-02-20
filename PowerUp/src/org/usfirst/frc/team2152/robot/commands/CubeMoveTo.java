package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class CubeMoveTo extends Command {

	double cubeRaiseSpeed;
	double cubeLowerSpeed;
	int cubeLeftTriggerid = 2;
	int cubeRightTriggerid = 3;
	Joystick joystick;

	/**
	 * This command controls the cube arm to raise and lower the manipulator.
	 * 
	 * @param cubeRaiseSpeed
	 *            Sets lower speed
	 * @param cubeLowerSpeed
	 *            Sets raise speed
	 * @param joystick
	 */
	public CubeMoveTo(double cubeRaiseSpeed, double cubeLowerSpeed, Joystick joystick) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.cubeMoveSubsystem);
		this.cubeRaiseSpeed = cubeRaiseSpeed;
		this.cubeLowerSpeed = cubeLowerSpeed;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		// Using else if so that only one trigger is usable at a time in the
		// following priority: LT, RT

		// When the Left trigger is pressed and the
		// max limit is not hit it will move the arm up.
		if (joystick.getRawAxis(cubeLeftTriggerid) == .1 && Robot.cubeMoveSubsystem.getCubeHighLimitValue() == false) {
			Robot.cubeMoveSubsystem.setCubeRaiseSpeed(cubeRaiseSpeed);

		} else if (Robot.cubeMoveSubsystem.getCubeHighLimitValue() == true) {
			Robot.cubeMoveSubsystem.setCubeRaiseSpeed(0);
		}

		// When the right trigger is pressed and the
		// min limit is not hit it will move the arm down.
		else if (joystick.getRawAxis(cubeRightTriggerid) >= .1
				&& Robot.cubeMoveSubsystem.getCubeLowLimitValue() == false) {
			Robot.cubeMoveSubsystem.setCubeLowerSpeed(cubeLowerSpeed);
		} else if (Robot.cubeMoveSubsystem.getCubeLowLimitValue() == true) {
			Robot.cubeMoveSubsystem.setCubeLowerSpeed(0);
		}

		// If either triggers are not pressed set the speeds to 0.
		else if (joystick.getRawAxis(cubeLeftTriggerid) == 0 && joystick.getRawAxis(cubeRightTriggerid) == 0) {
			Robot.cubeMoveSubsystem.setCubeRaiseSpeed(0);
			Robot.cubeMoveSubsystem.setCubeLowerSpeed(0);
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.cubeMoveSubsystem.setCubeRaiseSpeed(0);
		Robot.cubeMoveSubsystem.setCubeLowerSpeed(0);
	}
}
