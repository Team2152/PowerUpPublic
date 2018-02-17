package org.usfirst.frc.team2152.robot.subsystems;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.RobotMap;
import org.usfirst.frc.team2152.robot.commands.Elevate;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem{
	public static final double DEFAULT_SPEED = 0.5;
	
	private WPI_TalonSRX oneMotor;
	
	
	public Elevator() {
		
		oneMotor = new WPI_TalonSRX(2);
		oneMotor.setSafetyEnabled(false);
		oneMotor.setInverted(true);
	}

	/*
	 * you need to:
	 * 		create motors
	 * 		create any ports (i.e. limitSwitch = RobotMap.limitSwitch)
	 * 		create a constructor that can create a Elevator object
	 * 			-in this initialize the motors
	 * 		create any methods you will need(i.e. move(double inches) )
	 * 
	 * 		***if you need help ask for help or look at previous code***
	 */
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new Elevate(.2, Robot.m_oi.driverXbox));
		}
		
//		public void speed(double speed) {
//			oneMotor.set(speed);
//		}
		
		public void stop (){
			oneMotor.set(0);
			
		}
		public void up(double speed) {
			oneMotor.set(-speed);
		}
		
		public void down(double speed) {
			oneMotor.set(speed);
		}
	}


