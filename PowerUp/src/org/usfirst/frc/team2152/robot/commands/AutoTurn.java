package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.utilities.PIDConstants;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTurn extends Command implements PIDOutput {
	private double power;
	private double angle;
	PIDController pidTurn;
	Timer timer;

	private double pidOutput;

	public AutoTurn(double power, double angle) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrainSubsystem);
		requires(Robot.navxSubsystem);
		this.power = -power;
		this.angle = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer = new Timer();
		pidTurn = new PIDController(PIDConstants.AUTO_TURN_Kp, PIDConstants.AUTO_TURN_Ki, PIDConstants.AUTO_TURN_Kd,
				Robot.navxSubsystem.getAHRS(), this);
		pidTurn.disable();
		pidTurn.setAbsoluteTolerance(PIDConstants.AUTO_TURN_TOLERANCE);
		pidTurn.setOutputRange(-0.5, 0.5);
		pidTurn.setInputRange(-180, 180);
		pidTurn.setContinuous();
		pidTurn.setSetpoint(angle);
		timer.reset();
		timer.start();
		Robot.navxSubsystem.resetAngle();
		pidTurn.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrainSubsystem.arcadeDrive(power, pidOutput);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Math.abs(pidTurn.getError()) <= PIDConstants.AUTO_TURN_TOLERANCE) {
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		pidTurn.disable();
		Robot.driveTrainSubsystem.arcadeDrive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		pidTurn.disable();
		Robot.driveTrainSubsystem.arcadeDrive(0, 0);
	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		output = pidOutput;

	}
}
