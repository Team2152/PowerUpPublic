package org.usfirst.frc.team2152.robot.network;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class NetworkPIDSourcePitch implements PIDSource {
	private PIDSourceType pidSourcePitch = PIDSourceType.kDisplacement;

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		this.pidSourcePitch = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return pidSourcePitch;

	}

	@Override
	public double pidGet() {
		if (pidSourcePitch == PIDSourceType.kDisplacement) {
			return Robot.udp.getValue(Vars.Cube.Double.Pitch);
		} else {
			return Robot.udp.getValue(Vars.Cube.Double.Pitch);
		}
	}

}
