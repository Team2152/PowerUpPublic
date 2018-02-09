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
	private Victor cubeIntakeRight;
	private Victor cubeIntakeLeft;
	
	private DigitalInput cubeDetectOuterRight;
	private DigitalInput cubeDetectOuterLeft;
	private DigitalInput cubeDetectInnerRight;
	private DigitalInput cubeDetectInnerLeft;
	
	
	public CubeIntake(){

		cubeIntakeRight = new Victor(RobotMap.CUBE_INTAKE_RIGHT);
		cubeIntakeLeft = new Victor(RobotMap.CUBE_INTAKE_LEFT);
		
		cubeSolenoid = new DoubleSolenoid(0, 1);
		cubeSolenoid.set(DoubleSolenoid.Value.kReverse);
		
		
		cubeDetectOuterRight = new DigitalInput(RobotMap.DIO_4);
		cubeDetectOuterLeft  = new DigitalInput(RobotMap.DIO_5);
		cubeDetectInnerRight = new DigitalInput(RobotMap.DIO_6);
		cubeDetectInnerLeft  = new DigitalInput(RobotMap.DIO_7);
		
		
	}
	
	public void cubeIntakeMove(double cubeIntakeSpeed){
		cubeIntakeRight.set(-cubeIntakeSpeed);
		cubeIntakeLeft.set(cubeIntakeSpeed);
		
	}
	
	public void cubeExpelMove(double cubeExpelSpeed){
		cubeIntakeRight.set(cubeExpelSpeed);
		cubeIntakeLeft.set(-cubeExpelSpeed);
	}
	
	public void cubeRotateRight(double cubeRotateSpeed){
		cubeIntakeRight.set(cubeRotateSpeed);
		cubeIntakeLeft.set(cubeRotateSpeed);
	}
	
	public void cubeRotateLeft(double cubeRotateLeftSpeed){
		cubeIntakeRight.set(-cubeRotateLeftSpeed);
		cubeIntakeLeft.set(-cubeRotateLeftSpeed);
	}
	
	public void cubeSolenoidOpen(){
		cubeSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void cubeSolenoidClose(){
		cubeSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public boolean cubeDetectOutRight(){
		return cubeDetectOuterRight.get();
	}

	public boolean cubeDetectOutLeft(){
		return cubeDetectOuterLeft.get();
		
	}
	
	public boolean cubeDetectInRight(){
		return !cubeDetectInnerRight.get();
	}
	
	public boolean cubeDetectInLeft(){
		return !cubeDetectInnerLeft.get();
		
	}
	
	
	
	
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new CubeIntakeMove(.5, .5, .5, .5, Robot.m_oi.operatorXbox));
	}
}
