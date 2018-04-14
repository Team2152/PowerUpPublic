package org.usfirst.frc.team2152.robot.subsystems;

import org.usfirst.frc.team2152.robot.RobotMap;
import org.usfirst.frc.team2152.robot.commands.ClimberMoveJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    private WPI_TalonSRX elevatorTalon1;
    private WPI_TalonSRX elevatorTalon2;
    
	public Climber(){
		elevatorTalon1 = new WPI_TalonSRX(RobotMap.CLIMBER_MOVE_10_CAN_ID);
		elevatorTalon1.setInverted(false);
		elevatorTalon1.setNeutralMode(NeutralMode.Brake);
		elevatorTalon1.configOpenloopRamp(0, 10);
		elevatorTalon2 = new WPI_TalonSRX(RobotMap.CLIMBER_MOVE_6_CAN_ID);
		elevatorTalon2.setInverted(false);
		elevatorTalon2.setNeutralMode(NeutralMode.Brake);
		elevatorTalon2.configOpenloopRamp(0, 10);

		
	}
	
	public void setSpeed(double speed){
		elevatorTalon1.set(speed);
		elevatorTalon2.set(speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ClimberMoveJoystick());
    }
}

