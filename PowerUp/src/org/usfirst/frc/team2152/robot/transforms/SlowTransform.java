package org.usfirst.frc.team2152.robot.transforms;

public class SlowTransform implements ITransform{
	
	private double DAMPEN_VALUE = 0.65;

	@Override
	public double transform(double input) {
		
		return input * DAMPEN_VALUE;
	}

}
