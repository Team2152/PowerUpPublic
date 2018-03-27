package org.usfirst.frc.team2152.robot.subsystems;

import org.usfirst.frc.team2152.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    private WPI_TalonSRX elevatorTalon;
    
	public Climber(){
		elevatorTalon = new WPI_TalonSRX(RobotMap.CLIMBER_MOVE_10_CAN_ID);
	}
	
	public void setSpeed(double speed){
		elevatorTalon.set(speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

