package org.usfirst.frc.team2152.robot.utilities;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;


public class POV extends Button{
	private int pov = 0;
	private Joystick joystick;
	
	public POV(Joystick joystick, int pov){
		this.pov = pov;
		this.joystick = joystick;
	}
	
	@Override
	public boolean get() {
		if(joystick.getPOV() == pov){
			return true;
		} else {
		return false;
		}
	}
}
