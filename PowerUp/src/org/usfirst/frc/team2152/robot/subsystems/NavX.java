package org.usfirst.frc.team2152.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

import org.usfirst.frc.team2152.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

/**
 *
 */
public class NavX extends Subsystem {

	AHRS navx;
	
	private double last_world_linear_accel_y = 0;

	private final static double kCollisionThreshold_DeltaG = 0.1f;
	
	// Initialize your subsystem here
	public NavX() {
		try {
			/** Communicate w/navX-MXP via the MXP SPI Bus. */
			/*
			 * Alternatively: I2C.Port.kMXP, SerialPort.Port.kMXP or
			 * SerialPort.Port.kUSB
			 */
			/*
			 * See
			 * http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/
			 * for details.
			 */
			navx = new AHRS(SPI.Port.kMXP);
			resetAngle();
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
		}
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new NAVXGetAngle());
	}
	/**
	 * Returns object <code>navx<code>
	 * @return navx
	 */
	public AHRS getAHRS() {
		return navx;
	}
	
	/**
	 * Resets current angle recorded by the NavX to 0
	 */
	public void resetAngle() {
		navx.reset();
	}
	public Boolean detectCollision(){
		Timer.delay(0.005);		// wait for a motor update time
		double curr_world_linear_accel_y = Robot.navxSubsystem.getWorldLinearAccelY();
		double currentJerkY = curr_world_linear_accel_y - last_world_linear_accel_y;
		last_world_linear_accel_y = curr_world_linear_accel_y;

		if ( Math.abs(currentJerkY) > kCollisionThreshold_DeltaG) {
			return true;
		} else {
			return false;
		}


	}
	/**
	 * Returns current recorded angle from NavX
	 * @return double angle
	 */
	public double getAngle() {
		return navx.getAngle();
	}
	
	public double getWorldLinearAccelX() {
		return navx.getWorldLinearAccelX();
	}

	public double getWorldLinearAccelY() {
		return navx.getWorldLinearAccelY();
	}

	public double getWorldLinearAccelZ() {
		return navx.getWorldLinearAccelZ();
	}
}