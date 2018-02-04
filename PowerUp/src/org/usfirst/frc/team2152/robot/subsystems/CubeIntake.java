package org.usfirst.frc.team2152.robot.subsystems;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.RobotMap;
import org.usfirst.frc.team2152.robot.commands.CubeIntakeMove;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeIntake extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	DoubleSolenoid cubeSolenoid;
	private WPI_TalonSRX cubeIntakeRight;
	private WPI_TalonSRX cubeIntakeLeft;
	
	
	
	public CubeIntake(){

		cubeIntakeRight = new WPI_TalonSRX(RobotMap.CUBE_INTAKE_RIGHT);
		cubeIntakeLeft = new WPI_TalonSRX(RobotMap.CUBE_INTAKE_LEFT);
		
		cubeSolenoid = new DoubleSolenoid(0, 1);
		cubeSolenoid.set(DoubleSolenoid.Value.kReverse);
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
	
	
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new CubeIntakeMove(.5, .5, .5, .5, Robot.m_oi.operatorXbox));
	}
}
