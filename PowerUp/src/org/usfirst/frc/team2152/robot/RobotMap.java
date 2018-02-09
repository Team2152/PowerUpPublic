/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2152.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	// === Steamworks Drive Train Subsystem IDs
	public static final int LEFT_DRIVE_1_CAN_Id  = 1;
	public static final int LEFT_DRIVE_2_CAN_Id  = 2;
	public static final int LEFT_DRIVE_3_CAN_Id  = 3;

	public static final int RIGHT_DRIVE_1_CAN_Id = 4;
	public static final int RIGHT_DRIVE_2_CAN_Id = 5;
	public static final int RIGHT_DRIVE_3_CAN_Id = 6;
	
	public static final int RIGHT_ENCODER_A_DI   = 0;
	public static final int RIGHT_ENCODER_B_DI   = 1;
	public static final int LEFT_ENCODER_A_DI    = 2;
	public static final int LEFT_ENCODER_B_DI    = 3;
	
	public static final int DIO_0 = 0; // ENCODER 1A
	public static final int DIO_1 = 1; // ENCODER 1B
	public static final int DIO_2 = 2; // ENCODER 2A
	public static final int DIO_3 = 3; // ENCODER 2B
	
	
	public static final int DIO_4 = 4; // Distance Sensor
	public static final int DIO_5 = 5; // Distance Sensor
	public static final int DIO_6 = 6; // Distance Sensor
	public static final int DIO_7 = 7; // Distance Sensor
	
	public static final int CUBE_INTAKE_RIGHT     = 0;
	public static final int CUBE_INTAKE_LEFT      = 2;
//	public static final int LIFT_MOVE_1_CAN_ID    = 2; 
	
}
