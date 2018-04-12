package org.usfirst.frc.team2152.robot.network;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class NetworkPIDSourceRoll implements PIDSource {
	private PIDSourceType pidSourceRoll = PIDSourceType.kDisplacement;

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		this.pidSourceRoll = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return pidSourceRoll;

	}

	@Override
	public double pidGet() {
		if (pidSourceRoll == PIDSourceType.kDisplacement) {
			return Robot.udp.getValue(Vars.Cube.Double.Roll);
		} else {
			return Robot.udp.getValue(Vars.Cube.Double.Roll);
		}
	}

}
