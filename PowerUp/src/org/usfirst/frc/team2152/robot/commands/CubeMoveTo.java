package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CubeMoveTo extends Command {

	private double upGain = 0.4;
	private double downGain = 0.4;
	Joystick joystick;

	/**
	 * This command controls the cube arm to raise and lower the manipulator.
	 * 
	 * @param cubeRaiseSpeed
	 *            Sets raise speed
	 * @param cubeLowerSpeed
	 *            Sets lower speed
	 * @param joystick
	 */
	public CubeMoveTo(Joystick joystick) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.cubeMoveSubsystem);

		this.joystick = joystick;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("OLeftTrigger", joystick.getRawAxis(5) * .5);
		SmartDashboard.putNumber("ORightTrigger", joystick.getRawAxis(5) * .5);
		SmartDashboard.putBoolean("isHighPosition", Robot.cubeMoveSubsystem.isHighPosition());
		SmartDashboard.putBoolean("isLowPosition", Robot.cubeMoveSubsystem.isLowPosition());
		// Using else if so that only one trigger is usable at a time in the
		// following priority: LT, RT

		// When the Left trigger is pressed and the
		// max limit is not hit it will move the arm up.
		if (joystick.getRawAxis(5) <= -.1 && !Robot.cubeMoveSubsystem.isLowPosition()) {
			Robot.cubeMoveSubsystem.setCubeRaiseSpeed(joystick.getRawAxis(5) * 0.4);
		} else if (joystick.getRawAxis(5) >= .1 && !Robot.cubeMoveSubsystem.isHighPosition()) {
			Robot.cubeMoveSubsystem.setCubeRaiseSpeed(joystick.getRawAxis(5) * 0.4);
		} else {
			Robot.cubeMoveSubsystem.setCubeRaiseSpeed(0);
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
