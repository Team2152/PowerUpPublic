package org.usfirst.frc.team2152.robot.utilities;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.buttons.Button;

public class POV extends Button{
	private int pov = 0;
	public POV(int pov){
		this.pov = pov;
	}
	
	@Override
	public boolean get() {
		if(Robot.m_oi.driverXbox.getPOV() == pov){
			return true;
		} else {
		return false;
		}
	}

}
