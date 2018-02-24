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

	private DoubleSolenoid cubeSolenoid;
	private Victor cubeIntakeRight;
	private Victor cubeIntakeLeft;

	private DigitalInput cubeDetectOuterRight;
	private DigitalInput cubeDetectOuterLeft;
	private DigitalInput cubeDetectIn;

	public CubeIntake() {

		cubeIntakeRight = new Victor(RobotMap.CUBE_INTAKE_RIGHT_PWM_0);
		cubeIntakeLeft = new Victor(RobotMap.CUBE_INTAKE_LEFT_PWM_1);

		cubeSolenoid = new DoubleSolenoid(0, 1);
		cubeSolenoidClose();
		cubeDetectOuterRight = new DigitalInput(RobotMap.CUBE_DETECT_DIO_0);
		cubeDetectOuterLeft = new DigitalInput(RobotMap.CUBE_DETECT_DIO_1);
		cubeDetectIn = new DigitalInput(RobotMap.CUBE_DETECT_DIO_2);
		
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

	
	// Sets speed for auto
	public void cubeSetMoveSpeed(double cubeMoveSpeed){
		cubeIntakeRight.set(cubeMoveSpeed);
		cubeIntakeLeft.set(-cubeMoveSpeed);
	}
	// Opens the solenoid
	public void cubeSolenoidOpen() {
		cubeSolenoid.set(DoubleSolenoid.Value.kForward);
		
		// Tells drivers the solenoid has been opened
		Robot.powerUpDashboard.putCubeSolenoid(true);
	}

	// Closes the solenoid
	public void cubeSolenoidClose() {
		cubeSolenoid.set(DoubleSolenoid.Value.kReverse);
		
		// Tells drivers the solenoid has been closed
		Robot.powerUpDashboard.putCubeSolenoid(false);
	}
	
	public void cubeSolenoidToggle(){
		if(cubeSolenoid.get() == DoubleSolenoid.Value.kReverse){
			cubeSolenoidOpen();
		}else{
			cubeSolenoidClose();
		}
	}

	// If the sharp sensor detects a cube it will return true
	// The value is inverted because sharp sensors return false when something
	// is detected
	public boolean cubeDetectOutRight() {
		return !cubeDetectOuterRight.get();
	}

	// If the sharp sensor detects a cube it will return true
	// The value is inverted because sharp sensors return false when something
	// is detected
	public boolean cubeDetectOutLeft() {
		return !cubeDetectOuterLeft.get();

	}

	// If the sharp sensor detects a cube it will return true
	// The value is inverted because sharp sensors return false when something
	// is detected
	public boolean cubeDetectIn() {
		return !cubeDetectIn.get();
	}

	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new CubeIntakeMove(.8, 1, Robot.m_oi.operatorXbox));
	}
}
