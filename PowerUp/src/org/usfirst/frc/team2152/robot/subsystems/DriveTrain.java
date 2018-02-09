package org.usfirst.frc.team2152.robot.subsystems;

import org.usfirst.frc.team2152.robot.RobotMap;
import org.usfirst.frc.team2152.robot.commands.LimeDrive;
import org.usfirst.frc.team2152.robot.commands.TankDriveJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private static final int kSlotIdx = 0;
	private static final int kPIDLoopIdx = 0;
	private static final int kTimeoutMs = 10;
	private static double kP = 0.1;//.046;
	private static double kI = 0;//.00007;
	private static double kD = 0;//.0;
	
	private WPI_TalonSRX right1;
	private WPI_TalonSRX right2;
	private WPI_TalonSRX right3;
	private WPI_TalonSRX left1;
	private WPI_TalonSRX left2;
	private WPI_TalonSRX left3;
	

	// === Drive Train Object
	private DifferentialDrive drive;

	public DriveTrain() {

		// Create TalonSRX Objects for each of the motors
		right1 = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_1_CAN_Id);
		right1.setNeutralMode(NeutralMode.Brake);
		right1.setInverted(true);

		right2 = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_2_CAN_Id);
		right2.setNeutralMode(NeutralMode.Brake);
		right2.set(ControlMode.Follower,RobotMap.RIGHT_DRIVE_1_CAN_Id);
		right2.setInverted(true);

		right3 = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_3_CAN_Id);
		right3.setNeutralMode(NeutralMode.Brake);
		right3.set(ControlMode.Follower,RobotMap.RIGHT_DRIVE_1_CAN_Id);
		right3.setInverted(true);
		
		left1 = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_1_CAN_Id);
		left1.setNeutralMode(NeutralMode.Brake);
		left1.setInverted(true);
		
		left2 = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_2_CAN_Id);
		left2.setNeutralMode(NeutralMode.Brake);
		left2.set(ControlMode.Follower,RobotMap.LEFT_DRIVE_1_CAN_Id);
		left2.setInverted(true);
		
		left3 = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_3_CAN_Id);
		left3.setNeutralMode(NeutralMode.Brake);
		left3.set(ControlMode.Follower,RobotMap.LEFT_DRIVE_1_CAN_Id);
		left3.setInverted(true);

		drive = new DifferentialDrive(left1,right1);
		drive.setSafetyEnabled(false);
		
		
		right1.configNominalOutputForward(0, kTimeoutMs);
		right1.configNominalOutputReverse(0, kTimeoutMs);
		right1.configPeakOutputForward(1, kTimeoutMs);
		right1.configPeakOutputReverse(-1, kTimeoutMs);
		right1.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs);
		right1.config_kF(kPIDLoopIdx, 0.0, kTimeoutMs);
		right1.config_kP(kPIDLoopIdx, kP, kTimeoutMs);
		right1.config_kI(kPIDLoopIdx, kI, kTimeoutMs);
		right1.config_kD(kPIDLoopIdx, kD, kTimeoutMs);
		right1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		right1.setSensorPhase(true);
		
		
		left1.configNominalOutputForward(0, kTimeoutMs);
		left1.configNominalOutputReverse(0, kTimeoutMs);
		left1.configPeakOutputForward(1, kTimeoutMs);
		left1.configPeakOutputReverse(-1, kTimeoutMs);
		left1.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs);
		left1.config_kF(kPIDLoopIdx, 0.0, kTimeoutMs);
		left1.config_kP(kPIDLoopIdx, kP, kTimeoutMs);
		left1.config_kI(kPIDLoopIdx, kI, kTimeoutMs);
		left1.config_kD(kPIDLoopIdx, kD, kTimeoutMs);
		left1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		left1.setSensorPhase(false);
		
	}

	/***
	 * 
	 * Move motors using Tank Drive
	 * @param leftSpeed 
	 * 					from -1 to 1
	 * @param rightSpeed 
	 * 					from -1 to 1
	 */
	public void tankDrive(double leftSpeed, double rightSpeed) {
		drive.tankDrive(leftSpeed, rightSpeed);
	}
	
	/**
	 * Arcade drive implements single stick driving.
	 * 
	 * @param forward
	 *            The value to use for forwards/backwards
	 * @param turn
	 *            The value to use for the rotate right/left
	 */
	public void arcadeDrive(double forward, double turn) {
		drive.arcadeDrive(forward, turn, false);

	}

	/**
	 * Set the speed of the right motors
	 * 
	 * @param speed
	 *            from -1 to 1
	 */
	public void setRightSpeed(double speed) {
		right1.set(speed);
	}

	/**
	 * Set the speed of the left motors
	 * 
	 * @param speed
	 *            from -1 to 1
	 */
	public void setLeftSpeed(double speed) {
		left1.set(speed);
	}
	
	
	/**
	 * Returns the position of the encoder that is connected to the lead right motor controller
	 * @return the value of the sensor position as a double
	 */
	public double getRSensorPosition(){
		return right1.getSelectedSensorPosition(0);
	}
	
	/**
	 * Returns the position of the encoder that is connected to the lead left motor controller
	 * @return the value of the sensor position as a double
	 */
	public double getLSensorPosition(){
		return left1.getSelectedSensorPosition(0);
	}
	
	public void resetEncoders(boolean leftReset, boolean rightReset){
		if (leftReset){
			left1.setSelectedSensorPosition(0, 0, 0);
		}
		
		if (rightReset){
			right1.setSelectedSensorPosition(0, 0, 0);
		}
	}
	
	public void moveByPosition(double setPointLeft, double setPointRight){
		left1.set(ControlMode.Position, setPointLeft);
		right1.set(ControlMode.Position, setPointRight);
		
	}
	
	public void invertMotors(boolean leftInvert, boolean rightInvert,boolean leftSensorInvert,boolean rightSensorInvert){
		left1.setInverted(leftInvert);
		left2.setInverted(leftInvert);
		left3.setInverted(leftInvert);
		right1.setInverted(rightInvert);
		right2.setInverted(rightInvert);
		right3.setInverted(rightInvert);
		
		left1.setSensorPhase(leftSensorInvert);
		right1.setSensorPhase(rightSensorInvert);
	}
	
	public void setPID(double p, double i, double d){
		kP = p;
		kI = i;
		kD = d;
	}
	
	public double getP(){
		return kP;
	}
	
	public double getI(){
		return kI;
	}
	
	public double getD(){
		return kD;
	}
	
	
	



	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		//setDefaultCommand(new LimeDrive());
		setDefaultCommand(new TankDriveJoystick());
	}
}

