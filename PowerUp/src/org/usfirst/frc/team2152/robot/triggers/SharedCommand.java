package org.usfirst.frc.team2152.robot.triggers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SharedCommand extends Trigger{
	private Joystick joy1;
	private int button1;
	private Joystick joy2;
	private int button2;
	public SharedCommand(Joystick joy1, int button1, Joystick joy2, int button2){
		this.joy1 = joy1;
		this.button1 = button1;
		this.joy2 = joy2;
		this.button2 = button2;
	}
    public boolean get() {
    	if(joy1.getRawButton(button1) == true || joy2.getRawButton(button2) == true){
    		return true;
    	} else {
        return false;
    	}
    }
}
