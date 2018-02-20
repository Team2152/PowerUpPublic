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

	// DriveTrain
	public static final int RIGHT_DRIVE_1_CAN_ID     = 1;
	public static final int RIGHT_DRIVE_2_CAN_ID     = 2;
	public static final int RIGHT_DRIVE_3_CAN_ID     = 3;

	public static final int LEFT_DRIVE_1_CAN_ID      = 4;
	public static final int LEFT_DRIVE_2_CAN_ID      = 5;
	public static final int LEFT_DRIVE_3_CAN_ID      = 6;

	// Cube
	public static final int CUBE_ARM_MOVE_8_CAN_ID   = 8;

	// Elevator
	public static final int ELEVATOR_MOVE_9_CAN_ID   = 9;

	// PWM channel
	public static final int CUBE_INTAKE_RIGHT_PWM_0  = 0;
	public static final int CUBE_INTAKE_LEFT_PWM_1   = 1;
	public static final int LED_ARDUINO_PWM_4        = 4;

	//DIO channel
	public static final int CUBE_DETECT_DIO_0        = 0; // Distance Sensor
	public static final int CUBE_DETECT_DIO_1        = 1; // Distance Sensor
	public static final int CUBE_DETECT_DIO_2        = 2; // Distance Sensor
	public static final int CUBE_DETECT_DIO_3        = 3; // Distance Sensor
	public static final int CUBE_LIMIT_MAX_DIO_4     = 4;
	public static final int CUBE_LIMIT_MIN_DIO_5     = 5;
	public static final int ELEVATOR_MAX_LIMIT_DIO_6 = 6;
	public static final int ELEVATOR_MIN_LIMIT_DIO_7 = 7;






}
