package org.usfirst.frc.team2152.robot.subsystems;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.RobotMap;
import org.usfirst.frc.team2152.robot.commands.CubeIntakeMove;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CubeIntake extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	DoubleSolenoid cubeSolenoid;
	// private WPI_TalonSRX cubeIntakeRight;
	// private WPI_TalonSRX cubeIntakeLeft;

	private Victor cubeIntakeRight;
	private Victor cubeIntakeLeft;

	private DigitalInput cubeDetectOuterRight;
	private DigitalInput cubeDetectOuterLeft;
	private DigitalInput cubeDetectInnerRight;
	private DigitalInput cubeDetectInnerLeft;

	public CubeIntake() {

		// cubeIntakeRight = new WPI_TalonSRX(RobotMap.CUBE_INTAKE_RIGHT);
		// cubeIntakeLeft = new WPI_TalonSRX(RobotMap.CUBE_INTAKE_LEFT);

		cubeIntakeRight = new Victor(RobotMap.CUBE_INTAKE_RIGHT);
		cubeIntakeLeft = new Victor(RobotMap.CUBE_INTAKE_LEFT);

		cubeSolenoid = new DoubleSolenoid(0, 1);
		cubeSolenoid.set(DoubleSolenoid.Value.kReverse);

		cubeDetectOuterRight = new DigitalInput(RobotMap.CUBE_DETECT_DIO_4);
		cubeDetectOuterLeft = new DigitalInput(RobotMap.CUBE_DETECT_DIO_5);
		cubeDetectInnerRight = new DigitalInput(RobotMap.CUBE_DETECT_DIO_6);
		cubeDetectInnerLeft = new DigitalInput(RobotMap.CUBE_DETECT_DIO_7);

	}

	// Sets intake speed
	public void cubeIntakeMove(double cubeIntakeSpeed) {
		cubeIntakeRight.set(-cubeIntakeSpeed);
		cubeIntakeLeft.set(cubeIntakeSpeed);

	}

	// Sets expell speed
	public void cubeExpelMove(double cubeExpelSpeed) {
		cubeIntakeRight.set(cubeExpelSpeed);
		cubeIntakeLeft.set(-cubeExpelSpeed);
	}

	// Sets rotate right speed
	public void cubeRotateRight(double cubeRotateRightSpeed) {
		cubeIntakeRight.set(cubeRotateRightSpeed);
		cubeIntakeLeft.set(cubeRotateRightSpeed);
	}

	// Sets rotate left speed
	public void cubeRotateLeft(double cubeRotateLeftSpeed) {
		cubeIntakeRight.set(-cubeRotateLeftSpeed);
		cubeIntakeLeft.set(-cubeRotateLeftSpeed);
	}

	// Opens the solenoid
	public void cubeSolenoidOpen() {
		cubeSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	// Closes the solenoid
	public void cubeSolenoidClose() {
		cubeSolenoid.set(DoubleSolenoid.Value.kReverse);
	}

	//If the sharp sensor detects a cube it will return true
	//The value is inverted because sharp sensors return false when something is detected
	public boolean cubeDetectOutRight() {
		return !cubeDetectOuterRight.get();
	}

	//If the sharp sensor detects a cube it will return true
	//The value is inverted because sharp sensors return false when something is detected
	public boolean cubeDetectOutLeft() {
		return !cubeDetectOuterLeft.get();

	}

	//If the sharp sensor detects a cube it will return true
	//The value is inverted because sharp sensors return false when something is detected
	public boolean cubeDetectInRight() {
		return !cubeDetectInnerRight.get();
	}

	//If the sharp sensor detects a cube it will return true
	//The value is inverted because sharp sensors return false when something is detected
	public boolean cubeDetectInLeft() {
		return !cubeDetectInnerLeft.get();

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new CubeIntakeMove(.5, .5, .5, .5, Robot.m_oi.operatorXbox));
	}
}
