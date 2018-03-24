package org.usfirst.frc.team2152.robot;

import edu.wpi.first.wpilibj.Joystick;

public class ControllerMap {
	// -----Shared Commands-----
	public static int expelCubeDriver = OI.buttonBumpRid;
	public static int expelCubeOperator = OI.buttonXid;
	
	public static int acquireCubeDriver = OI.buttonBumpLid;
	public static int acquireCubeOperator = OI.buttonStartid;
	
	public static int raiseCubeDriver = OI.buttonYid;
	public static int raiseCubeOperator = OI.buttonAid;
	
	public static int clampCubeDriver = OI.buttonXid;
	public static int clampCubeOperator = OI.buttonBid;
	
	public static int lowerCubeDriver = OI.buttonAid;
	public static int lowerCubeOperator = OI.buttonYid;
	
	public static int cubeFinesseDriver = OI.POV_0;
	public static int cubeFinesseOperator = OI.buttonBackid;
	
	public static int acquireCubeExchangeDriver = OI.POV_180;
	public static int acquireCubeExchangeOperator = OI.POV_180;
	
	// -----Drive Train-----
	public static Joystick limeDriveThrottle = Robot.m_oi.driverXbox;
	public static Joystick limeDriveTurn = Robot.m_oi.driverXbox;
	public static Joystick limeDriveAssistTurn = Robot.m_oi.operatorXbox;
	
	// -----Cube Intake-----
	public static Joystick cubeIntakeJoy1 = Robot.m_oi.driverXbox;
	public static Joystick cubeIntakeJoy2 = Robot.m_oi.driverXbox;
	 
	public static int cubeIntakeIntakeAxisDriver = 3;
	public static int cubeIntakeIntakeAxisOperator = 3;
	
	public static int cubeIntakeExpelAxisDriver = 2;
	public static int cubeIntakeExpelAxisOperator = 3;
	
	// -----Elevator-----
	public static Joystick elevatorJoystick = Robot.m_oi.operatorXbox;
	
	public static int elevatorMoveAxis = 1;
	
	
}
