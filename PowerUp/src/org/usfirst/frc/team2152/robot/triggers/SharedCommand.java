package org.usfirst.frc.team2152.robot.triggers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SharedCommand extends Button{
	
	public final static int BUTTON_MODE = 0;
	public final static int POV_MODE = 1;
	private Joystick joy1;
	private int button1;
	private Joystick joy2;
	private int button2;
	private int mode;
	
	public SharedCommand(Joystick joy1, int button1, Joystick joy2, int button2, int mode){
		this.joy1 = joy1;
		this.button1 = button1;
		this.joy2 = joy2;
		this.button2 = button2;
		this.mode = mode;
	}
    public boolean get() {
    	
    	switch(mode){
    	
    	case(BUTTON_MODE):
    		if(joy1.getRawButton(button1) == true || joy2.getRawButton(button2) == true){
        		return true;
        	} else {
            return false;
        	}
        	
    	case(POV_MODE):
    		if(joy1.getPOV() == button1 || joy2.getPOV() == button2){
    			return true;
    		} else {
    			return false;
    		}
    	default:
    		return false;
    	}
    }
}
