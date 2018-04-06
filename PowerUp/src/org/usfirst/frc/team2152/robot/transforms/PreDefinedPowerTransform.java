package org.usfirst.frc.team2152.robot.transforms;

public class PreDefinedPowerTransform implements ITransform {
	
	private double PREDEFINED_POWER =  0.1;

	@Override
	public double transform(double input) {
		return PREDEFINED_POWER;
	}

}
