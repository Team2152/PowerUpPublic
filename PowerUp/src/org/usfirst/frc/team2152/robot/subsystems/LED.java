package org.usfirst.frc.team2152.robot.subsystems;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.SafePWM;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LED extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Servo ledSignal;
	
	public LED() {
		ledSignal = new Servo(9);
		
		//ledSignal.setRaw(150);
	}
	
	public void setValue(int value) {
		ledSignal.setAngle(value);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

