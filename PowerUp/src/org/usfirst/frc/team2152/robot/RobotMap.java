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
	public static final int CUBE_DETECT_DIO_RIGHT    = 0; // Distance Sensor outer right
	public static final int CUBE_DETECT_DIO_LEFT     = 1; // Distance Sensor outer left
	public static final int CUBE_DETECT_DIO_CENTER   = 2; // Distance Sensor inner 
	public static final int CUBE_LIMIT_DIO_BOTTOM    = 3;
	public static final int CUBE_LIMIT_DIO_TOP       = 4;
	public static final int ELEVATOR_MAX_LIMIT_DIO_5 = 5;
	public static final int ELEVATOR_MIN_LIMIT_DIO_6 = 6;


	/*
	 * UDP Network Ports/IPs
	 * L means LOCAL: RIO binds to these ports
	 * R means REMOTE: ODROID binds to these ports
	 */

	public static final String LIDAR_ODROID_IP = "10.21.52.12";

	public static final String ODROID_1_IP = "10.21.52.11";
	public static final String ODROID_2_IP = "10.21.52.12";
	public static final String ODROID_3_IP = "10.21.52.13";
	
	public static final int UDP_VISION_PORT_L = 5800;
	public static final int UDP_CMD_PORT_L    = 5808;

	public static final int UDP_SYNC_CONF_PORT_R           = 5808;
	public static final int UDP_SYNC_TIME_PORT_R           = UDP_SYNC_CONF_PORT_R;
	public static final int UDP_ENCODER_SEND_PORT_R        = 5809;
	public static final int UDP_ODROID_CAM_SETTINGS_PORT_R = 5810;



}
