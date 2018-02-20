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



	//DriveTrain Motor IDs
	public static final int RIGHT_DRIVE_1_CAN_Id = 1;
	public static final int RIGHT_DRIVE_2_CAN_Id = 2;
	public static final int RIGHT_DRIVE_3_CAN_Id = 3;
	
	public static final int LEFT_DRIVE_1_CAN_Id  = 5;
	public static final int LEFT_DRIVE_2_CAN_Id  = 6;
	public static final int LEFT_DRIVE_3_CAN_Id  = 7;
	
	
	public static final int ELEVATOR_MAX_LIMIT_DIO_6 = 6;
	public static final int ELEVATOR_MAX_LIMIT_DIO_7 = 7;
	public static final int ELEVATOR_MOVE_10_CAN_ID  = 9;


	public static final int CUBE_DETECT_DIO_4  = 0; // Distance Sensor
	public static final int CUBE_DETECT_DIO_5  = 1; // Distance Sensor
	public static final int CUBE_DETECT_DIO_6  = 2; // Distance Sensor
	public static final int CUBE_DETECT_DIO_7  = 3; // Distance Sensor
		public static final int CUBE_LIMIT_MAX = 4;
	public static final int CUBE_LIMIT_MIN     = 5;
	
	public static final int CUBE_INTAKE_RIGHT  = 0;
	public static final int CUBE_INTAKE_LEFT   = 1;
	public static final int ARM_MOVE_1_CAN_ID  = 8;


}
