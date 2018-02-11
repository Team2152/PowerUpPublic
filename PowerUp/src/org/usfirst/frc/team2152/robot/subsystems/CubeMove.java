//package org.usfirst.frc.team2152.robot.subsystems;
//
//import org.usfirst.frc.team2152.robot.Robot;
//import org.usfirst.frc.team2152.robot.RobotMap;
//import org.usfirst.frc.team2152.robot.commands.CubeMoveTo;
//
//import com.ctre.phoenix.motorcontrol.FeedbackDevice;
//import com.ctre.phoenix.motorcontrol.NeutralMode;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//
//import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//
///**
// *
// */
//public class CubeMove extends Subsystem {
//
//	// Put methods for controlling this subsystem
//	// here. Call these from Commands.
//
//	private WPI_TalonSRX cubeMoveTalon;
//	
//	
//	
//
//	public CubeMove(){
//		cubeMoveTalon = new WPI_TalonSRX(RobotMap.LIFT_MOVE_1_CAN_ID);
//		cubeMoveTalon.setNeutralMode(NeutralMode.Brake);
//	
//		cubeMoveTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute,0 ,0);
//		cubeMoveTalon.setSensorPhase(false);	
//		
//		
//		
//		
//	}
//
//	public double getCubeEncoderPosition(){
//		double getCubeEncoderPosition = cubeMoveTalon.getSelectedSensorPosition(0);
//		SmartDashboard.putNumber("Cube Encoder Position", getCubeEncoderPosition);
//		return getCubeEncoderPosition;
//	}
//	
//	public void setCubeRaiseSpeed(double cubeMoveSpeed) {
//		cubeMoveTalon.set(cubeMoveSpeed);	
//	}
//	
//	public void setCubeLowerSpeed(double cubeMoveSpeed){
//		cubeMoveTalon.set(-cubeMoveSpeed);
//	}
//
//	public void initDefaultCommand() {
//		// Set the default command for a subsystem here.
//		// setDefaultCommand(new MySpecialCommand());
//		setDefaultCommand(new CubeMoveTo(.5, .5, Robot.m_oi.operatorXbox));
//	}
//}
