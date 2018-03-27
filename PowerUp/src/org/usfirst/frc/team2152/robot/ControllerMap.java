package org.usfirst.frc.team2152.robot;

import edu.wpi.first.wpilibj.Joystick;

public class ControllerMap {
	// -----Shared Commands-----
	private static Joystick driver = null;
	private static Joystick operator = null;
	public static Joystick elevatorJoystick = null;// Robot.m_oi.operatorXbox;
	public static Joystick limeDriveThrottle = null;// Robot.m_oi.driverXbox;
	public static Joystick limeDriveTurn = null;// Robot.m_oi.driverXbox;
	public static Joystick limeDriveAssistTurn = null;// Robot.m_oi.operatorXbox;
	public static Joystick cubeIntakeJoy1 = null; // Robot.m_oi.driverXbox;
	public static Joystick cubeIntakeJoy2 = null; // Robot.m_oi.driverXbox;
	public static Joystick climberMoveJoystick = null;

	public static int expelCubeDriver = 1; // OI.buttonBumpRid;
	public static int expelCubeOperator = 1; // OI.buttonXid;

	public static int acquireCubeDriver = 1; // OI.buttonBumpLid;
	public static int acquireCubeOperator = 1; // OI.buttonBumpLid;

	public static int raiseCubeDriver = 1; // OI.buttonYid;
	public static int raiseCubeOperator = 1; // OI.buttonAid;

	public static int clampCubeDriver = 1; // OI.buttonXid;
	public static int clampCubeOperator = 1; // OI.buttonBid;

	public static int lowerCubeDriver = 1; // OI.buttonAid;
	public static int lowerCubeOperator = 1; // OI.buttonYid;

	public static int cubeFinesseDriver = 1; // OI.POV_0;
	public static int cubeFinesseOperator = 1; // OI.buttonStartid;
	
	
	
	public static int acquireCubeExchangeDriver = 1; // OI.POV_180;
	public static int acquireCubeExchangeOperator = 1; // OI.buttonBackid;
	public static int cubeIntakeIntakeAxisDriver = 1; // 3;
	public static int cubeIntakeIntakeAxisOperator = 1; // 3;

	public static int cubeIntakeExpelAxisDriver = 1; // 2;
	public static int cubeIntakeExpelAxisOperator = 1; // 3;
	public static int elevatorMoveAxis = 1; // 1;
	
	

	// Operator
	public static int elevatorMoveHigh = 1; // OI.POV_0;
	public static int elevatorMoveLow = 1; // OI.POV_180;
	
	// Climber 
	public static int climberMoveOperator = 1;

	public static void setControllers(Joystick driver, Joystick operator) {

		expelCubeOperator = OI.buttonXid;

		acquireCubeDriver = OI.buttonBumpLid;
		acquireCubeOperator = OI.buttonBumpLid;

		raiseCubeDriver = OI.buttonYid;
		raiseCubeOperator = OI.buttonAid;

		clampCubeDriver = OI.buttonXid;
		clampCubeOperator = OI.buttonBid;

		lowerCubeDriver = OI.buttonAid;
		lowerCubeOperator = OI.buttonYid;

		cubeFinesseDriver = OI.POV_0;
		cubeFinesseOperator = OI.buttonStartid;

		acquireCubeExchangeDriver = OI.POV_180;
		acquireCubeExchangeOperator = OI.buttonBackid;
		
		

		// -----Drive Train-----
		limeDriveThrottle = driver;// Robot.m_oi.driverXbox;
		limeDriveTurn = driver;// Robot.m_oi.driverXbox;
		limeDriveAssistTurn = operator;// Robot.m_oi.operatorXbox;

		// -----Cube Intake-----
		cubeIntakeJoy1 = driver; // Robot.m_oi.driverXbox;
		cubeIntakeJoy2 = operator; // Robot.m_oi.driverXbox;

		cubeIntakeIntakeAxisDriver = 3;
		cubeIntakeIntakeAxisOperator = 3;

		cubeIntakeExpelAxisDriver = 2;
		cubeIntakeExpelAxisOperator = 3;

		// -----Elevator-----
		elevatorJoystick = operator;// Robot.m_oi.operatorXbox;

		elevatorMoveAxis = 1;

		// Operator
		elevatorMoveHigh = OI.POV_0;
		elevatorMoveLow = OI.POV_180;
		
		// -----Climber-----
		climberMoveJoystick = operator;
		climberMoveOperator = OI.buttonBumpRid;
	}

}
