    package org.usfirst.frc.team2152.robot.subsystems;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.RobotMap;
import org.usfirst.frc.team2152.robot.commands.ElevatorMove;
import org.usfirst.frc.team2152.robot.utilities.PIDConstants;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

	private WPI_TalonSRX elevatorTalon;
	private DigitalInput elevatorMaxHeight;
	private DigitalInput elevatorMinHeight;

	public Elevator() {

		elevatorTalon = new WPI_TalonSRX(RobotMap.ELEVATOR_MOVE_9_CAN_ID);
		elevatorTalon.setSafetyEnabled(false);
		elevatorTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		elevatorTalon.setSensorPhase(true);

		elevatorMaxHeight = new DigitalInput(RobotMap.ELEVATOR_MAX_LIMIT_DIO_5);
		elevatorMinHeight = new DigitalInput(RobotMap.ELEVATOR_MIN_LIMIT_DIO_6);
	}
	public double getElevatorCurrentDraw(){
		return elevatorTalon.getOutputCurrent();
	}

	public void setElevatorRaiseSpeed(double raiseSpeed) {
		elevatorTalon.set(-raiseSpeed);
	}

	public void setElevatorLowerSpeed(double lowerSpeed) {
		elevatorTalon.set(lowerSpeed);
	}

	public void setElevatorStop() {
		elevatorTalon.set(0);

	}
	
	public double getEncoder(){
		return elevatorTalon.getSelectedSensorPosition(0);
	}
	
	public double getDriveTrainGain(){
		return elevatorTalon.getSelectedSensorPosition(0)* PIDConstants.ELEVATOR_DRIVETRAIN_GAIN;
	}
	public Boolean getElevatorMaxHeight() {
		return !elevatorMaxHeight.get();
	}

	public Boolean getElevatorMinHeight() {
		return !elevatorMinHeight.get();
	}

	@Override
	protected void initDefaultCommand() {

		setDefaultCommand(new ElevatorMove(.1, Robot.m_oi.operatorXbox));
	}

}
