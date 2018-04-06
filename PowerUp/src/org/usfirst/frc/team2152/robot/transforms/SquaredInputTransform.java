package org.usfirst.frc.team2152.robot.transforms;

public class SquaredInputTransform implements ITransform {

	/**
	 * Returns square of input while maintaining sign
	 */
	@Override
	public double transform(double input) {
		return Math.copySign(input*input, input);
	}

}
