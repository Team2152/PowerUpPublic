package org.usfirst.frc.team2152.robot.subsystems;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.RobotMap;
import org.usfirst.frc.team2152.robot.commands.LimeDrive;
import org.usfirst.frc.team2152.robot.network.NetworkPIDSourceDistance;
import org.usfirst.frc.team2152.robot.network.NetworkPIDSourceXAngle;
import org.usfirst.frc.team2152.robot.utilities.PIDConstants;
import org.usfirst.frc.team2152.robot.utilities.TalonPIDSource;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain extends Subsystem{
	// Note: MoveByPosition (Not MoveByEncoder) inversion settings = Robot.driveTrainSubsystem.invertDriveTrain(false, true, true, true);
	// Note: Normal joystick and pid drive inversion settings = Robot.driveTrainSubsystem.invertDriveTrain(true, true, false, true);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public static final double DISTANCE_PER_PULSE = (Math.PI * 6) / 4096;
	private static final int kSlotIdx = 0;
	private static final int kPIDLoopIdx = 0;
	private static final int kTimeoutMs = 10;
	private static double kP = 2.6;
	private static double kI = 0;
	private static double kD = 0;

	private WPI_TalonSRX right1;
	private WPI_TalonSRX right2;
//	private WPI_TalonSRX right3;
	private WPI_TalonSRX left1;
	private WPI_TalonSRX left2;
//	private WPI_TalonSRX left3;

	private int encoderCumulativeTicksL = 0;
	private int encoderCumulativeTicksR = 0;

	// === Drive Train Object
	private DifferentialDrive drive;

	private TalonPIDSource talonPIDL;
	private TalonPIDSource talonPIDR;
	
	private NetworkPIDSourceDistance networkPIDSourceDistance; 
	private NetworkPIDSourceXAngle networkPIDSourceXAngle;

	public DriveTrain() {




		// Create TalonSRX Objects for each of the motors
		right1 = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_1_CAN_ID);
		right1.setNeutralMode(NeutralMode.Brake);
		right1.setInverted(true);
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
		right1.configOpenloopRamp(PIDConstants.CONTROLLER_DRIVE_RAMP_RATE, PIDConstants.CONTROLLER_DRIVE_RAMP_TIMEOUT);

		right2 = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_2_CAN_ID);
		right2.setNeutralMode(NeutralMode.Brake);
		right2.set(ControlMode.Follower,RobotMap.RIGHT_DRIVE_1_CAN_ID);
		right2.setInverted(true);
		right2.configOpenloopRamp(PIDConstants.CONTROLLER_DRIVE_RAMP_RATE, PIDConstants.CONTROLLER_DRIVE_RAMP_TIMEOUT);

//		right3 = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_3_CAN_ID);
//		right3.setNeutralMode(NeutralMode.Brake);
//		right3.set(ControlMode.Follower,RobotMap.RIGHT_DRIVE_1_CAN_ID);
//		right3.setInverted(true);
//		right3.configOpenloopRamp(PIDConstants.CONTROLLER_DRIVE_RAMP_RATE, PIDConstants.CONTROLLER_DRIVE_RAMP_TIMEOUT);

		
		left1 = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_1_CAN_ID);
		left1.setNeutralMode(NeutralMode.Brake);
		left1.setInverted(true);
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
		left1.configOpenloopRamp(PIDConstants.CONTROLLER_DRIVE_RAMP_RATE, PIDConstants.CONTROLLER_DRIVE_RAMP_TIMEOUT);
		
		left2 = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_2_CAN_ID);
		left2.setNeutralMode(NeutralMode.Brake);
		left2.set(ControlMode.Follower,RobotMap.LEFT_DRIVE_1_CAN_ID);
		left2.setInverted(true);
		left2.configOpenloopRamp(PIDConstants.CONTROLLER_DRIVE_RAMP_RATE, PIDConstants.CONTROLLER_DRIVE_RAMP_TIMEOUT);

		
//		left3 = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_3_CAN_ID);
//		left3.setNeutralMode(NeutralMode.Brake);
//		left3.set(ControlMode.Follower,RobotMap.LEFT_DRIVE_1_CAN_ID);
//		left3.setInverted(true);
//		left3.configOpenloopRamp(PIDConstants.CONTROLLER_DRIVE_RAMP_RATE, PIDConstants.CONTROLLER_DRIVE_RAMP_TIMEOUT);

		
		drive = new DifferentialDrive(left1,right1);
		drive.setSafetyEnabled(false);



		talonPIDR = new TalonPIDSource(TalonPIDSource.RIGHT_TALON);
		talonPIDL = new TalonPIDSource(TalonPIDSource.LEFT_TALON);
		
		networkPIDSourceDistance = new NetworkPIDSourceDistance();
		right1.getSelectedSensorVelocity(0);
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
	/**
	 * Returns the distance of the right encoder in inches
	 * @return the value of the encoder in inches as a double
	 */
	public double getRDistance(){
		return right1.getSelectedSensorPosition(0) * DISTANCE_PER_PULSE;
	}

	/**
	 * Returns the distance of the left encoder in inches
	 * @return the value of the encoder in inches as a double
	 */
	public double getLDistance(){
		return left1.getSelectedSensorPosition(0) * DISTANCE_PER_PULSE;
	}
	
	public double getAverageDistance(){
		return (getRDistance() + getLDistance())/2; 
	}

	/**
	 * Returns the cumulative ticks of the right encoder
	 * @return the cumulative (ignoring resets) value of the encoder in ticks
	 */
	public int getEncoderRTicksCumulative() {
		return right1.getSelectedSensorPosition(0) + encoderCumulativeTicksR;
	}

	/**
	 * Returns the cumulative ticks of the left encoder
	 * @return the cumulative (ignoring resets) value of the encoder in ticks
	 */
	public int getEncoderLTicksCumulative() {
		return left1.getSelectedSensorPosition(0) + encoderCumulativeTicksR;
	}

	
	
	/**
	 * Resets the encoders
	 * @param resetLeft determines whether or not to reset the left encoder
	 * @param resetRight determines whether or not to reset the right encoder
	 */
	public void resetEncoders(boolean resetLeft, boolean resetRight){
		if (resetLeft){
			encoderCumulativeTicksL += left1.getSelectedSensorPosition(0);
			left1.setSelectedSensorPosition(0, 0, 0);
		}

		if (resetRight){
			encoderCumulativeTicksR += right1.getSelectedSensorPosition(0);
			right1.setSelectedSensorPosition(0, 0, 0);
		}
	}
	
	/**
	 * Resets cumulative encoder values
	 * @param l determines whether or not to reset the left encoder cumulative value
	 * @param r determines whether or not to reset the right encoder cumulative value
	 */
	public void resetCumulativeEncoderValues(boolean l, boolean r) {
		if(l) encoderCumulativeTicksL = 0;
		if(r) encoderCumulativeTicksR = 0;
	}
	
	/**
	 * Uses Phoenix's move by position control mode
	 * @param setPointLeft left setpoint in encoder ticks
	 * @param setPointRight right setpoint in encoder ticks
	 */
	public void moveByPosition(double setPointLeft, double setPointRight){
		right1.configPeakOutputForward(.45, kTimeoutMs);
		right1.configPeakOutputReverse(-.45, kTimeoutMs);
		left1.configPeakOutputForward(.45, kTimeoutMs);
		left1.configPeakOutputReverse(-.45, kTimeoutMs);
		left1.set(ControlMode.Position, setPointLeft);
		right1.set(ControlMode.Position, setPointRight);

	}
	/**
	 * Inverts the drive train motors and encoders
	 * @param leftInvert determines whether or not to invert the left motors
	 * @param rightInvert determines whether or not to invert the right motors
	 * @param leftSensorInvert determines whether or not to invert the left encoder 
	 * @param rightSensorInvert determines whether or not to invert the right encoder
	 */
	public void invertDriveTrain(boolean leftInvert, boolean rightInvert,boolean leftSensorInvert,boolean rightSensorInvert){
		left1.setInverted(leftInvert);
		left2.setInverted(leftInvert);
//		left3.setInverted(leftInvert);
		right1.setInverted(rightInvert);
		right2.setInverted(rightInvert);
//		right3.setInverted(rightInvert);

		left1.setSensorPhase(leftSensorInvert);
		right1.setSensorPhase(rightSensorInvert);
	}

	/**
	 * Returns the left encoder as a PIDSource
	 * @param type encoding type
	 * @return the left encoder as a PIDSource
	 */
	public TalonPIDSource getLTalonDistancePID(PIDSourceType type) {
		talonPIDL.setPIDSourceType(type);
		return talonPIDL;
	}
	/**
	 * Returns the right encoder as a PIDSource
	 * @param type encoding type
	 * @return the right encoder as a PIDSource
	 */
	public TalonPIDSource getRTalonDistancePID(PIDSourceType type) {
		talonPIDR.setPIDSourceType(type);
		return talonPIDR;
	}
	
	/**
	 * Returns the Current draw of the selected motor
	 * @param talonID the id of the talon to read
	 * @return
	 */
	public double getCurrent(int talonID){
		switch(talonID){
		case(1):
			return right1.getOutputCurrent();
		case(2):
			return right2.getOutputCurrent();
//		case(3):
//			return right3.getOutputCurrent();
		case(4):
			return left1.getOutputCurrent();
		case(5):
			return left2.getOutputCurrent();
//		case(6):
//			return left3.getOutputCurrent();
		default:
			return 0;
		}
	}
	
	public double avgCurrent(int valToAVG){
		switch(valToAVG){
		case(1):
			return (Robot.driveTrainSubsystem.getCurrent(4) + 
					(Robot.driveTrainSubsystem.getCurrent(5) + 
					(Robot.driveTrainSubsystem.getCurrent(6))))/3;
		case(2):
			return  (Robot.driveTrainSubsystem.getCurrent(1) + 
					(Robot.driveTrainSubsystem.getCurrent(2) + 
					(Robot.driveTrainSubsystem.getCurrent(3))))/3;
		case(3):
			return (Robot.driveTrainSubsystem.getCurrent(1) +
					Robot.driveTrainSubsystem.getCurrent(2) +
					Robot.driveTrainSubsystem.getCurrent(3) +
					Robot.driveTrainSubsystem.getCurrent(4) +
					Robot.driveTrainSubsystem.getCurrent(5) +
					Robot.driveTrainSubsystem.getCurrent(6))/6;
		default:
			return 0;
		}
	}
	
	/**
	 * Sets the ramp rate of the drive train motors
	 * @param secondsToFull the number of seconds till the motor is at the selected speed
	 * @param timeOut
	 */
	public void setRampRate(double secondsToFull, int timeOut){
		left1.configOpenloopRamp(secondsToFull, timeOut);
		right1.configOpenloopRamp(secondsToFull, timeOut);
	}
	
	/**
	 * Resets the ramp rate of the drive train motors
	 */
	public void resetRampRate(){
		left1.configOpenloopRamp(PIDConstants.CONTROLLER_DRIVE_RAMP_RATE, PIDConstants.CONTROLLER_DRIVE_RAMP_TIMEOUT);
		right1.configOpenloopRamp(PIDConstants.CONTROLLER_DRIVE_RAMP_RATE, PIDConstants.CONTROLLER_DRIVE_RAMP_TIMEOUT);
	}
	
	/**
	 * Sets the neutral mode of the drive train motors
	 * @param isBreakMode selects whether or not the motors' neutral mode is break mode or not
	 */
	public void setBreakMode(boolean isBreakMode){
		if(isBreakMode == true){
			right1.setNeutralMode(NeutralMode.Brake);
			right2.setNeutralMode(NeutralMode.Brake);
//			right3.setNeutralMode(NeutralMode.Brake);
			
			left1.setNeutralMode(NeutralMode.Brake);
			left2.setNeutralMode(NeutralMode.Brake);
//			left3.setNeutralMode(NeutralMode.Brake);
			
		} else {
			right1.setNeutralMode(NeutralMode.Coast);
			right2.setNeutralMode(NeutralMode.Coast);
//			right3.setNeutralMode(NeutralMode.Coast);
			
			left1.setNeutralMode(NeutralMode.Coast);
			left2.setNeutralMode(NeutralMode.Coast);
//			left3.setNeutralMode(NeutralMode.Coast);

		}
	}
	
	/**
	 * Returns the velocity of the right side of the drivetrain
	 * @return the velocity of the right side of the drivetrain
	 */
	public double getRVelocity(){
		return right1.getSelectedSensorVelocity(0);
	}

	/**
	 * Returns the velocity of the left side of the drivetrain
	 * @return the velocity of the left side of the drivetrain
	 */
	public double getLVelocity(){
		return left1.getSelectedSensorVelocity(0);
	}

	public PIDSource getNetDistancePID(PIDSourceType type) {
		networkPIDSourceDistance.setPIDSourceType(type);
		return networkPIDSourceDistance;
	}

	public PIDSource getNetXanglePID(PIDSourceType type) {
		networkPIDSourceXAngle.setPIDSourceType(type);
		return networkPIDSourceXAngle;
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new LimeDrive());
		//setDefaultCommand(new TankDriveJoystick());
	}

	


}

