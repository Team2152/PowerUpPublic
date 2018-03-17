package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.OI;
import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

			// Using else if so that only one button is usable at a time in the
			// following priority: A X
			// A button intakes until inner sensors are triggered
			if (driverJoystick.getRawButton(OI.buttonBid) == true) {
				Robot.cubeIntakeSubsystem.cubeIntakeMove(cubeIntakeSpeed);
			}
			else if(driverJoystick.getPOV() == 0 ){
				Robot.cubeIntakeSubsystem.cubeSolenoidOpen();
				Robot.cubeIntakeSubsystem.cubeExpelMove(.25);
			}
			
			
			
			else if (operatorJoystick.getRawAxis(3) > 0.1) {
				Robot.cubeIntakeSubsystem.cubeIntakeMove(operatorJoystick.getRawAxis(3));
			} else {
				Robot.cubeIntakeSubsystem.cubeIntakeMove(0);
				Robot.cubeIntakeSubsystem.cubeExpelMove(0);

			}

		} else {
			// If the INNER IR sensor is triggered then stop all intake motors
			Robot.cubeIntakeSubsystem.cubeIntakeMove(0);
			Robot.cubeIntakeSubsystem.cubeExpelMove(0);

		}

		// If the INNER IR sensor is triggered then it will allow the user to
		// expel the cube
		if (Robot.cubeIntakeSubsystem.cubeDetectIn() == true) {
			if (driverJoystick.getRawAxis(3) > .1){
				if(Robot.navxSubsystem.detectCollision() == true){
					cubeOuttakeSensor.start();
				}
			}
			if (driverJoystick.getRawButton(OI.buttonBumpRid) || operatorJoystick.getRawAxis(3) > 0.1) {
				Robot.cubeIntakeSubsystem.cubeExpelMove(operatorJoystick.getRawAxis(3));
			} else {
				Robot.cubeIntakeSubsystem.cubeExpelMove(0);
			}

		}

		// If BOTH OUTER sensors are triggered then the solenoid will actuate
		// and will close the clamp
		// They have to placed in order to see enough of the cube
//		if (Robot.cubeIntakeSubsystem.cubeDetectOutRight() == true
//				|| Robot.cubeIntakeSubsystem.cubeDetectOutLeft() == true) {
//			Robot.cubeIntakeSubsystem.cubeSolenoidClose();
//		}

		// This is the manual override for the solenoid clamp
		// Checks for a button press and will open and close the clamp
		// if (joystick.getRawButton(OI.buttonStartid) == true) {
		// Robot.cubeIntakeSubsystem.cubeSolenoidToggle();
		// }
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		// This is the default command for the CubeIntake Subsystem. Must return
		// false.
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
