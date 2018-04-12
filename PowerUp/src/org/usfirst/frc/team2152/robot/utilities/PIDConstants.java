package org.usfirst.frc.team2152.robot.utilities;

import org.usfirst.frc.team2152.robot.subsystems.DriveTrain;

public class PIDConstants {
	
	// Encoder Drive
	public static final double ENCODER_DRIVE_kP           = 0.012;
	public static final double ENCODER_DRIVE_kI           = 0.0000;
	public static final double ENCODER_DRIVE_kD           = 0.048;
	public static final double ENCODER_DRIVE_kTolerance   = 2 / DriveTrain.DISTANCE_PER_PULSE;
	public static final double ENCODER_DRIVE_SPEED        = 0.40;
	public static final double AUTO_DRIVE_RAMP_RATE       = .5;
	public static final int AUTO_DRIVE_RAMP_TIMEOUT       = 10;
	
	// Heading Hold
	public final static double HH_kP                      = 0.0076;
	public final static double HH_kI                      = 0.001;
	public final static double HH_dD                      = 0.008;
	public final static double HH_TOLERANCE               = 2.0;
	public final static float HH_IN_MIN                   = -180.0f;
	public final static float HH_IN_MAX                   = 180.0f;
	public final static double HH_OUT_MIN                 = -0.5;
	public final static double HH_OUT_MAX                 = 0.5;
	
	// Pre-Canned Turn
	public final static double PCT_Kp                     = 0.02;
	public final static double PCT_Ki                     = 0.00005775;   
	public final static double PCT_Kd                     = 0.048;
	public final static double PCT_TOLERANCE              = 4;
	public final static float PCT_IN_MIN                  = -180.0f;
	public final static float PCT_IN_MAX                  = 180.0f;
	
	// Cube Acquire Turn
		public final static double CAT_Kp                     = 0.005;
		public final static double CAT_Ki                     = 0.000057;   
		public final static double CAT_Kd                     = 0.048;
		public final static double CAT_TOLERANCE              = 4;
		public final static float CAT_IN_MIN                  = -180.0f;
		public final static float CAT_IN_MAX                  = 180.0f;
	
	// Cube Acquire Drive
		public static final double CAD_kP           = 0.0125;
		public static final double CAD_kI           = 0.0000;
		public static final double CAD_kD           = 0.048;
		public static final double CAD_kTolerance   = 2 / DriveTrain.DISTANCE_PER_PULSE;

	
		
	// Controller Drive
	public static final double CONTROLLER_DRIVE_RAMP_RATE = 0;
	public static final int CONTROLLER_DRIVE_RAMP_TIMEOUT = 10;
	
	// Elevator Drive
	public static final double ELEVATOR_DRIVETRAIN_GAIN   = 0.046511627907; //4/86
	
	// drivetrain gains
	public static final double DRIVETRAIN_THROTTLE_EXPONET = 2.5;
	public static final double DRIVETRAIN_TURN_EXPONET = 1.5; 
	
	public final static double AUTO_TURN_Kp                     = 0.02;
	public final static double AUTO_TURN_Ki                     = 0.00005775;   
	public final static double AUTO_TURN_Kd                     = 0.048;
	public final static double AUTO_TURN_TOLERANCE              = 2.5;
	public final static float AUTO_TURN_IN_MIN                  = -180.0f;
	public final static float AUTO_TURN_IN_MAX                  = 180.0f;
	
	public final static double ELEVATOR_Kf                     = 1.875;
	public final static double ELEVATOR_Kp                     = 34.9184;
	public final static double ELEVATOR_Ki                     = 0.001;   
	public final static double ELEVATOR_Kd                     = 1396.736;
	public final static double ELEVATOR_kTolerance			   = 2;

	
	
	
}
