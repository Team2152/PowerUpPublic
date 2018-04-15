package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.ControllerMap;
import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeIntakeMove extends Command {

	double cubeIntakeSpeed = 0;
	double cubeExpelSpeed = 0;

	Joystick operatorJoystick;
	Joystick driverJoystick;
	Command cubeOuttakeSensor;
	/**
	 * This command controls the cube intake and the solenoid. you can intake in
	 * and out and clamp via joystick. To see the joystick controls refer to the
	 * joystick spreadsheet.
	 * 
	 * @param cubeIntakeSpeed
	 *            Sets intake speed
	 * @param cubeExpelSpeed
	 *            Sets expel speed
	 * @param joystick
	 */
	public CubeIntakeMove(double cubeIntakeSpeed, double cubeExpelSpeed, Joystick driverJoystick,
			Joystick operatorJoystick) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.cubeIntakeSubsystem);
		this.cubeIntakeSpeed = cubeIntakeSpeed;
		this.cubeExpelSpeed = cubeExpelSpeed;
		this.driverJoystick = driverJoystick;
		this.operatorJoystick = operatorJoystick;
		cubeOuttakeSensor = new CubeExpelSensor(1);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		// When the inner IR sensor shows NO cube presence then
		// check then joystick buttons and act on them if they are pressed
		if (Robot.cubeIntakeSubsystem.cubeDetectIn() == false) {

			// A button intakes until inner sensors are triggered
			if (driverJoystick.getRawAxis(ControllerMap.cubeIntakeIntakeAxisDriver) >= 0.1) {

				Robot.cubeIntakeSubsystem.cubeIntakeMove(driverJoystick.getRawAxis(ControllerMap.cubeIntakeIntakeAxisDriver));

			} else if(driverJoystick.getRawAxis(ControllerMap.cubeIntakeExpelAxisDriver) >= 0.1){

				Robot.cubeIntakeSubsystem.cubeExpelMove(driverJoystick.getRawAxis(ControllerMap.cubeIntakeExpelAxisDriver));

			} else if (operatorJoystick.getRawAxis(ControllerMap.cubeIntakeIntakeAxisOperator) >= 0.1){

				Robot.cubeIntakeSubsystem.cubeIntakeMove(operatorJoystick.getRawAxis(ControllerMap.cubeIntakeExpelAxisOperator));

			} else if (operatorJoystick.getRawAxis(ControllerMap.cubeIntakeExpelAxisOperator) >= 0.1){

				Robot.cubeIntakeSubsystem.cubeExpelMove(ControllerMap.cubeIntakeExpelAxisOperator);

			} else {
				Robot.cubeIntakeSubsystem.cubeExpelMove(0);
			}
			// When the inner IR sensor shows NO cube presence then
			// check then joystick buttons and act on them if they are pressed
		} else if (Robot.cubeIntakeSubsystem.cubeDetectIn() == true) {
			
			if (driverJoystick.getRawAxis(ControllerMap.cubeIntakeExpelAxisDriver) >= 0.1) {

				Robot.cubeIntakeSubsystem.cubeExpelMove(driverJoystick.getRawAxis(ControllerMap.cubeIntakeExpelAxisDriver));

			} else if(operatorJoystick.getRawAxis(ControllerMap.cubeIntakeExpelAxisOperator) >= 0.1){

				Robot.cubeIntakeSubsystem.cubeExpelMove(operatorJoystick.getRawAxis(ControllerMap.cubeIntakeExpelAxisOperator));

			} else {
				Robot.cubeIntakeSubsystem.cubeExpelMove(0);
			}

		} else {
			Robot.cubeIntakeSubsystem.cubeIntakeMove(0);
			Robot.cubeIntakeSubsystem.cubeExpelMove(0);

		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		// This is the default command for the CubeIntake Subsystem. Must return false.
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.cubeIntakeSubsystem.cubeIntakeMove(0);
		Robot.cubeIntakeSubsystem.cubeExpelMove(0);
	}
}
