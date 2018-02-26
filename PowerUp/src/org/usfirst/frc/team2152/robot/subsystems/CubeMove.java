package org.usfirst.frc.team2152.robot.subsystems;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.RobotMap;
import org.usfirst.frc.team2152.robot.commands.CubeMoveTo;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CubeMove extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private WPI_TalonSRX cubeMoveTalon;
	private DigitalInput cubeLimitMax;
	private DigitalInput cubeLimitMin;

	public CubeMove() {
		cubeMoveTalon = new WPI_TalonSRX(RobotMap.CUBE_ARM_MOVE_8_CAN_ID);
		cubeMoveTalon.setNeutralMode(NeutralMode.Brake);

		cubeLimitMax = new DigitalInput(RobotMap.CUBE_LIMIT_MAX_DIO_3);
		cubeLimitMin = new DigitalInput(RobotMap.CUBE_LIMIT_MIN_DIO_4);

	}

	// Sets raise speed
	public void setCubeRaiseSpeed(double cubeRaiseSpeed) {
		cubeMoveTalon.set(cubeRaiseSpeed);
	}

	// Sets lower speed
	public void setCubeLowerSpeed(double cubeLowerSpeed) {
		cubeMoveTalon.set(-cubeLowerSpeed);
	}

	// Returns the state of the max sensor
	public boolean getCubeHighLimitValue() {
		return !cubeLimitMax.get();

	}

	// Returns the state of the min sensor
	public boolean getCubeLowLimitValue() {
		return !cubeLimitMin.get();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new CubeMoveTo(Robot.m_oi.operatorXbox));
	}
}