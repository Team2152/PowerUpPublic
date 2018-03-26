package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.utilities.PIDConstants;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorMoveTo extends Command implements PIDOutput {
	double position;
	double maxSpeed;
	
	PIDController controller;
	public ElevatorMoveTo(double position, double maxSpeed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.elevatorSubsystem);
		this.position = Robot.elevatorSubsystem.convertToNativeUnits(position);
		this.maxSpeed = maxSpeed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		controller = new PIDController(PIDConstants.ELEVATOR_Kp, 
				PIDConstants.ELEVATOR_Ki, PIDConstants.ELEVATOR_Kd, 
				Robot.elevatorSubsystem.getTalonDistancePID(PIDSourceType.kDisplacement), this);
		
		 controller.setAbsoluteTolerance(PIDConstants.ELEVATOR_kTolerance);
		 
		 controller.setSetpoint(position);
		 controller.setOutputRange(-0.1, maxSpeed);
		 controller.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.elevatorSubsystem.setElevatorRaiseSpeed(controller.get());
		System.out.println("------------" + controller.get());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (controller.onTarget()){
			return true;
		} else {
		return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		controller.disable();
		Robot.elevatorSubsystem.setElevatorRaiseSpeed(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		controller.disable();
		Robot.elevatorSubsystem.setElevatorRaiseSpeed(0);
	}

	@Override
	public void pidWrite(double output) {
		
	}
}
