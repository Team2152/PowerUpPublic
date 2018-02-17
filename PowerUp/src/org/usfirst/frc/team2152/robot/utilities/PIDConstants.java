package org.usfirst.frc.team2152.robot.utilities;

import org.usfirst.frc.team2152.robot.subsystems.DriveTrain;

public class PIDConstants {

	public static final double ENCODER_DRIVE_kP = .023;
	public static final double ENCODER_DRIVE_kI = 0.00001;
	public static final double ENCODER_DRIVE_kD = 0.052;
	public static final double ENCODER_DRIVE_kTolerance = 2 / DriveTrain.DISTANCE_PER_PULSE;
	
	public final static double HH_kP = 0.0076;
	public final static double HH_kI = 0.001;
	public final static double HH_dD = 0.008;
	public final static double HH_TOLERANCE = 2.0;
	public final static float HH_IN_MIN = -180.0f;
	public final static float HH_IN_MAX = 180.0f;
	public final static double HH_OUT_MIN = -0.5;
	public final static double HH_OUT_MAX = 0.5;
	
	public final static double PCT_Kp = .024;
	public final static double PCT_Ki = 0.00001;   
	public final static double PCT_Kd = 0.0564;
	public final static double PCT_TOLERANCE = 4;
	public final static float PCT_IN_MIN = -180.0f;
	public final static float PCT_IN_MAX = 180.0f;
}
