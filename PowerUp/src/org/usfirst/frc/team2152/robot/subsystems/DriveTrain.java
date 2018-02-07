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
	public static final int kSlotIdx = 0;

	/*
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
	 * now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;

	/*
	 * set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */
	public static final int kTimeoutMs = 10;
	
	/* choose so that Talon does not report sensor out of phase */
	public static boolean kSensorPhase = true;

	/* choose based on what direction you want to be positive,
		this does not affect motor invert. */
	public static boolean kMotorInvert = false;
	
	private static final double DISTANCE_PER_PULSE = 0.01112647398146385105288852864912;
	
	private WPI_TalonSRX right1;
	private WPI_TalonSRX right2;
	private WPI_TalonSRX right3;
	private WPI_TalonSRX left1;
	private WPI_TalonSRX left2;
	private WPI_TalonSRX left3;

	// === Steamworks Encoders
	private Encoder encoderR;
	private int encoderRA = RobotMap.DIO_0;
	private int encoderRB = RobotMap.DIO_1;

	private Encoder encoderL;
	private int encoderLA = RobotMap.DIO_2;
	private int encoderLB = RobotMap.DIO_3;
	

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
		
		
		//Steamworks encoders
		encoderR = new Encoder(encoderRA, encoderRB, true, EncodingType.k4X);
		encoderR.setDistancePerPulse(DISTANCE_PER_PULSE);
		encoderR.setSamplesToAverage(1);
		encoderR.reset();

		encoderL = new Encoder(encoderLA, encoderLB, false, EncodingType.k4X);
		encoderL.setDistancePerPulse(DISTANCE_PER_PULSE);
		encoderL.setSamplesToAverage(1);
		encoderL.reset();
		
		//right1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		
		right1.configNominalOutputForward(0, kTimeoutMs);
		right1.configNominalOutputReverse(0, kTimeoutMs);
		right1.configPeakOutputForward(1, kTimeoutMs);
		right1.configPeakOutputReverse(-1, kTimeoutMs);
		/*
		 * set the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		right1.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs);

		/* set closed loop gains in slot0, typically kF stays zero. */
		right1.config_kF(kPIDLoopIdx, 0.0, kTimeoutMs);
		right1.config_kP(kPIDLoopIdx, 0.1, kTimeoutMs);
		right1.config_kI(kPIDLoopIdx, 0.001, kTimeoutMs);
		right1.config_kD(kPIDLoopIdx, 0.0, kTimeoutMs);
		
		right1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		right1.setSensorPhase(true);
		
		
		left1.configNominalOutputForward(0, kTimeoutMs);
		left1.configNominalOutputReverse(0, kTimeoutMs);
		left1.configPeakOutputForward(1, kTimeoutMs);
		left1.configPeakOutputReverse(-1, kTimeoutMs);
		/*
		 * set the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		left1.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs);

		/* set closed loop gains in slot0, typically kF stays zero. */
		left1.config_kF(kPIDLoopIdx, 0.0, kTimeoutMs);
		left1.config_kP(kPIDLoopIdx, 0.1, kTimeoutMs);
		left1.config_kI(kPIDLoopIdx, 0.001, kTimeoutMs);
		left1.config_kD(kPIDLoopIdx, 0.0, kTimeoutMs);
		
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
	 * The inches on the right encoder
	 * 
	 * @return The distance in inches
	 */
	public double getRightDistance() {
		return (encoderR.getDistance());
	}

	/**
	 * The inches on the left encoder
	 * 
	 * @return The distance in inches
	 */

	public double getLeftDistance() {
		return (encoderL.getDistance());
	}

	public void resetAllEncoders() {
		encoderR.reset();
		encoderL.reset();
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
	 * returns the right encoder object to be used in PIDs
	 * 
	 * @param type
	 *            Which parameter of the encoder you are using as a process
	 *            control variable.
	 * @return the right encoder object
	 */
	public Encoder getEncoderR(PIDSourceType type) {
		encoderR.setPIDSourceType(type);
		return encoderR;
	}

	/**
	 * returns the left encoder object to be used in PIDs
	 * 
	 * @param type
	 *            Which parameter of the encoder you are using as a process
	 *            control variable.
	 * @return the left encoder object
	 */
	public Encoder getEncoderL(PIDSourceType type) {
		encoderL.setPIDSourceType(type);
		return encoderL;
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
	
	public void resetREncoder(){
		right1.setSelectedSensorPosition(0, 0, 0);
	}
	
	public void resetLEncoder(){
		left1.setSelectedSensorPosition(0, 0, 0);
	}
	
	public void moveByPosition(double setPoint){
		right1.set(ControlMode.Position, setPoint);
		left1.set(ControlMode.Position, setPoint);
	}
	
	
	



	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new LimeDrive());
		//setDefaultCommand(new TankDriveJoystick());
	}
}

