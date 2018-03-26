package org.usfirst.frc.team2152.robot.network;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class NetworkPIDSourceClosest implements PIDSource {
	private PIDSourceType pidSourceClosest = PIDSourceType.kDisplacement;

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		this.pidSourceClosest = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return pidSourceClosest;

	}

	@Override
	public double pidGet() {
		if (pidSourceClosest == PIDSourceType.kDisplacement) {
			return Robot.udp.getValue(Vars.Cube.Double.Closest);
		} else {
			return Robot.udp.getValue(Vars.Cube.Double.Closest);
		}
	}

}
