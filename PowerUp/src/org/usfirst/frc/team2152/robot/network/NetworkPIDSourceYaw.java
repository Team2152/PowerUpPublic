package org.usfirst.frc.team2152.robot.network;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class NetworkPIDSourceYaw implements PIDSource {
	private PIDSourceType pidSourceYaw = PIDSourceType.kDisplacement;

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		this.pidSourceYaw = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return pidSourceYaw;

	}

	@Override
	public double pidGet() {
		if (pidSourceYaw == PIDSourceType.kDisplacement) {
			return Robot.udp.getValue(Vars.Cube.Double.Yaw);
		} else {
			return Robot.udp.getValue(Vars.Cube.Double.Yaw);
		}
	}

}
