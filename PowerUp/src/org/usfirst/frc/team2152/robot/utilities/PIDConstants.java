package org.usfirst.frc.team2152.robot.utilities;

import org.usfirst.frc.team2152.robot.subsystems.DriveTrain;

public class PIDConstants {

	public static final double ENCODER_DRIVE_kP = .012;
	public static final double ENCODER_DRIVE_kI = 0.00006;
	public static final double ENCODER_DRIVE_kD = 0.048;
	public static final double ENCODER_DRIVE_kTolerance = 2 / DriveTrain.DISTANCE_PER_PULSE;
	
	public static final double AUTO_DRIVE_RAMP_RATE = 0;
	public static final int AUTO_DRIVE_RAMP_TIMEOUT = 10;
	
	public static final double CONTROLLER_DRIVE_RAMP_RATE = 0;
	public static final int CONTROLLER_DRIVE_RAMP_TIMEOUT = 10;
	
	public final static double HH_kP = 0.0076;
	public final static double HH_kI = 0.001;
	public final static double HH_dD = 0.008;
	public final static double HH_TOLERANCE = 2.0;
	public final static float HH_IN_MIN = -180.0f;
	public final static float HH_IN_MAX = 180.0f;
	public final static double HH_OUT_MIN = -0.5;
	public final static double HH_OUT_MAX = 0.5;
	
	public final static double PCT_Kp = 0.022;
	public final static double PCT_Ki = 0.000045;   
	public final static double PCT_Kd = 0.0556;
	public final static double PCT_TOLERANCE = 4;
	public final static float PCT_IN_MIN = -180.0f;
	public final static float PCT_IN_MAX = 180.0f;
}
