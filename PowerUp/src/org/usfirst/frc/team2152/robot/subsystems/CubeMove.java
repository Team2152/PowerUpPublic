package org.usfirst.frc.team2152.robot.subsystems;

import org.usfirst.frc.team2152.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CubeMove extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private WPI_TalonSRX cubeMoveTalon;
	private DigitalInput cubeLimitMax;
	private DigitalInput cubeLimitMin;

	public CubeMove() {
		cubeMoveTalon = new WPI_TalonSRX(RobotMap.CUBE_ARM_MOVE_8_CAN_ID);
		cubeMoveTalon.setInverted(false);
		cubeMoveTalon.setNeutralMode(NeutralMode.Brake);

		cubeLimitMax = new DigitalInput(RobotMap.CUBE_LIMIT_DIO_TOP);
		cubeLimitMin = new DigitalInput(RobotMap.CUBE_LIMIT_DIO_BOTTOM);

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
	public boolean isHighPosition() {
		if (cubeLimitMax.get() == false)
			return true;
		else
			return false;
	}

	// Returns the state of the min sensor
	public boolean isLowPosition() {
		if (cubeLimitMin.get() == false)
			return true;
		else
			return false;
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//setDefaultCommand(new CubeMoveTo(Robot.m_oi.operatorXbox));	
		}
}