package org.usfirst.frc.team2152.robot.subsystems;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.RobotMap;
import org.usfirst.frc.team2152.robot.commands.ElevatorMove;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

	public final double DEFAULT_UP_SPEED = 0.7;
	public final double DEFAULT_DOWN_SPEED = 0.1;
	private WPI_TalonSRX elevatorTalon;
	private DigitalInput maxLimitSwitch;
	private DigitalInput minLimitSwitch;
	public Elevator() {

		elevatorTalon = new WPI_TalonSRX(RobotMap.ELEVATOR_MOVE_10_CAN_ID);
		elevatorTalon.setSafetyEnabled(false);
		elevatorTalon.setInverted(true);
		
		maxLimitSwitch = new DigitalInput(RobotMap.ELEVATOR_MAX_LIMIT);
		minLimitSwitch = new DigitalInput(RobotMap.ELEVATOR_MIN_LIMIT);
		
	}

	public void setElevatorRaiseSpeed(double raiseSpeed) {
		if(maxLimitSwitch.get() == false) {
			elevatorTalon.set(raiseSpeed);
		}
	}

	public void setElevatorLowerSpeed(double lowerSpeed) {
		if(minLimitSwitch.get() == false) {
			elevatorTalon.set(-lowerSpeed);
		}
	}

	public void stop() {
		elevatorTalon.set(0);

	}
	
	public void reset() {
		while(minLimitSwitch.get() == false) {
			elevatorTalon.set(DEFAULT_DOWN_SPEED);
		}
	}
	
	public void setMaxHeight() {
		while(maxLimitSwitch.get() == false) {
			elevatorTalon.set(DEFAULT_UP_SPEED);
		}
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new ElevatorMove());
	}

}
