package org.usfirst.frc.team2152.robot.subsystems;

import org.usfirst.frc.team2152.robot.ControllerMap;
import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.RobotMap;
import org.usfirst.frc.team2152.robot.commands.ElevatorMove;
import org.usfirst.frc.team2152.robot.commands.MotionMagicElevatorMove;
import org.usfirst.frc.team2152.robot.utilities.PIDConstants;
import org.usfirst.frc.team2152.robot.utilities.TalonPIDSource;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

	private WPI_TalonSRX elevatorTalon;
	private DigitalInput elevatorMaxHeight;
	private DigitalInput elevatorMinHeight;
	private double eleStartingHeight = 21;

	private TalonPIDSource talonSource;

	public Elevator() {
		elevatorTalon = new WPI_TalonSRX(RobotMap.ELEVATOR_MOVE_9_CAN_ID);
		elevatorTalon.setInverted(true);
		elevatorTalon.setSafetyEnabled(false);
		elevatorTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		elevatorTalon.setSensorPhase(true);
		elevatorTalon.setNeutralMode(NeutralMode.Brake);
		elevatorTalon.configOpenloopRamp(0.9, 10);
		elevatorTalon.configNominalOutputForward(0, 0);
		elevatorTalon.configNominalOutputReverse(0, 0);
		elevatorTalon.configPeakOutputForward(1, 0);
		elevatorTalon.configPeakOutputReverse(-1, 0);

		elevatorTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 10);
		elevatorTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 10);
		elevatorTalon.selectProfileSlot(0, 0);
		elevatorTalon.config_kF(0, PIDConstants.ELEVATOR_Kf, 0);
		elevatorTalon.config_kP(0, PIDConstants.ELEVATOR_Kp, 0);
		elevatorTalon.config_kI(0, PIDConstants.ELEVATOR_Ki, 0);
		elevatorTalon.config_kD(0, PIDConstants.ELEVATOR_Kd, 0);
		elevatorTalon.configMotionCruiseVelocity(378, 10);
		elevatorTalon.configMotionAcceleration(378, 10);
		elevatorTalon.configReverseSoftLimitThreshold(0, 10);
		elevatorTalon.configReverseSoftLimitEnable(true, 10);

		elevatorMaxHeight = new DigitalInput(RobotMap.ELEVATOR_MAX_LIMIT_DIO_5);
		elevatorMinHeight = new DigitalInput(RobotMap.ELEVATOR_MIN_LIMIT_DIO_6);

		talonSource = new TalonPIDSource(TalonPIDSource.ELEVATOR_TALON);
	}

	public void goToHeight(double height) {
		elevatorTalon.set(ControlMode.MotionMagic, height);

	}

	public double getElevatorCurrentDraw() {
		return elevatorTalon.getOutputCurrent();
	}

	public double getEncoderVelocity() {
		return elevatorTalon.getSelectedSensorVelocity(0);
	}

	public void setElevatorRaiseSpeed(double raiseSpeed) {
		elevatorTalon.set(ControlMode.PercentOutput, -raiseSpeed);
	}

	public void setElevatorLowerSpeed(double lowerSpeed) {
		elevatorTalon.set(ControlMode.PercentOutput, lowerSpeed);
	}

	public void setElevatorStop() {
		elevatorTalon.set(ControlMode.PercentOutput, 0);

	}

	public void resetEleEncoder() {
		elevatorTalon.setSelectedSensorPosition((int) eleStartingHeight, 0, 0);
	}

	public double getEncoder() {
		return elevatorTalon.getSelectedSensorPosition(0);
	}

	public double getDriveTrainRampRate() {
		if (getEleInches() <= 22) {
			return 1;
		} else {
			return getEleInches() * PIDConstants.ELEVATOR_DRIVETRAIN_GAIN;
		}
	}

	public Boolean getElevatorMaxHeight() {
		return !elevatorMaxHeight.get();
	}

	public Boolean getElevatorMinHeight() {
		return !elevatorMinHeight.get();
	}

	public double getEleInches() {
		return (elevatorTalon.getSelectedSensorPosition(0) / (4096 / 24)) + eleStartingHeight;
	}

	public double convertToNativeUnits(double height) {
		return (height - eleStartingHeight) * (4096 / 24);
	}

	public TalonPIDSource getTalonDistancePID(PIDSourceType type) {
		talonSource.setPIDSourceType(type);
		return talonSource;
	}
	
	public void setEncoder(int encoderVal){
		elevatorTalon.setSelectedSensorPosition(encoderVal, 0, 10);
	}
	
	

	@Override
	protected void initDefaultCommand() {

		//setDefaultCommand(new ElevatorMove(.1, ControllerMap.elevatorJoystick));
		setDefaultCommand(new MotionMagicElevatorMove(.1, ControllerMap.elevatorJoystick));

	}

	public double getEleOutput() {
		return elevatorTalon.getMotorOutputPercent();
	}

}
