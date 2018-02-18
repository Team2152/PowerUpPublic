package org.usfirst.frc.team2152.robot.utilities;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class TalonPIDSource implements PIDSource{
	public static final int LEFT_TALON = 0;
	public static final int RIGHT_TALON = 1;
	
	private PIDSourceType pidSourceDistance = PIDSourceType.kDisplacement;
	private int talon = 0;
	
	public TalonPIDSource(int talon){
		this.talon = talon;
	}
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		this.pidSourceDistance = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return pidSourceDistance;
	}

	@Override
	public double pidGet() {
		if (talon == LEFT_TALON){
			return Robot.driveTrainSubsystem.getLSensorPosition();
		} else if (talon ==RIGHT_TALON){
			return Robot.driveTrainSubsystem.getRSensorPosition();
		} else {
			return 0;
		}
	}

}
