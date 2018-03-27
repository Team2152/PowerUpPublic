/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2152.robot;

import org.usfirst.frc.team2152.robot.commands.AcquireCube;
import org.usfirst.frc.team2152.robot.commands.AcquireCubeExchange;
import org.usfirst.frc.team2152.robot.commands.AutoCubeMoveHigh;
import org.usfirst.frc.team2152.robot.commands.AutoCubeMoveLow;
import org.usfirst.frc.team2152.robot.commands.ClimberMove;
import org.usfirst.frc.team2152.robot.commands.CubeExpel;
import org.usfirst.frc.team2152.robot.commands.CubeFinesse;
import org.usfirst.frc.team2152.robot.commands.CubeIntakeSensor;
import org.usfirst.frc.team2152.robot.commands.CubeMoveLow;
import org.usfirst.frc.team2152.robot.commands.CubeSolenoidToggle;
import org.usfirst.frc.team2152.robot.commands.ElevatorMoveHigh;
import org.usfirst.frc.team2152.robot.commands.ElevatorMoveLow;
import org.usfirst.frc.team2152.robot.commands.ElevatorMoveTo;
import org.usfirst.frc.team2152.robot.commands.LEDTest;
import org.usfirst.frc.team2152.robot.commands.MoveByEncoder;
import org.usfirst.frc.team2152.robot.commands.NavigateToCube;
//import org.usfirst.frc.team2152.robot.commands.MoveByPosition;
import org.usfirst.frc.team2152.robot.commands.PreCannedTurn;
import org.usfirst.frc.team2152.robot.commands.ResetEncoders;
import org.usfirst.frc.team2152.robot.commands.ResetNavx;
import org.usfirst.frc.team2152.robot.utilities.POV;
import org.usfirst.frc.team2152.robot.utilities.SharedCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those tob
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	public final static int POV_0 = 0;
	public final static int POV_45 = 45;
	public final static int POV_90 = 90;
	public final static int POV_135 = 135;
	public final static int POV_180 = 180;
	public final static int POV_225 = 225;
	public final static int POV_270 = 270;
	public final static int POV_315 = 315;

	public final static int OPERATOR_XBOX = 1;
	public final static int DRIVER_XBOX = 0;

	// === Axis ids; they are the same for each joystick
	public final static int XBOX_LEFT_XAXIS = 0;
	public final static int XBOX_LEFT_YAXIS = 1;
	public final static int XBOX_RIGHT_XAXIS = 4;
	public final static int XBOX_RIGHT_YAXIS = 5;

	public final static int XBOX_LEFT_TRIGGER = 2;
	public final static int XBOX_RIGHT_TRIGGER = 3;
	// public final static int SPEED_STICK = 999999; // Deoderant...always wear
	// it.
	public final static int buttonAid = 1;
	public final static int buttonBid = 2;
	public final static int buttonXid = 3;
	public final static int buttonYid = 4;
	public final static int buttonBumpLid = 5;
	public final static int buttonBumpRid = 6;
	public final static int buttonBackid = 7;
	public final static int buttonStartid = 8;
	public final static int buttonLClickid = 9;
	public final static int buttonRClickid = 10;

	public Joystick operatorXbox;
	private Button oButtonA;
	private Button oButtonB;
	private Button oButtonX;
	private Button oButtonY;
	private Button oButtonBumpL;
	private Button oButtonBumpR;
	private Button oButtonBack;
	private Button oButtonStart;
	private Button oButtonLClick;
	private Button oButtonRClick;
	private POV oPOV0;
	private POV oPOV45;
	private POV oPOV90;
	private POV oPOV135;
	private POV oPOV180;
	private POV oPOV225;
	private POV oPOV270;
	private POV oPOV315;

	public Joystick driverXbox;
	private Button dButtonA;
	private Button dButtonB;
	private Button dButtonX;
	private Button dButtonY;
	private Button dButtonBumpL;
	private Button dButtonBumpR;
	private Button dButtonBack;
	private Button dButtonStart;
	private Button dButtonLClick;
	private Button dButtonRClick;
	private POV dPOV0;
	private POV dPOV45;
	private POV dPOV90;
	private POV dPOV135;
	private POV dPOV180;
	private POV dPOV225;
	private POV dPOV270;
	private POV dPOV315;

	private SharedCommand expelCube;
	private SharedCommand acquireCube;
	private SharedCommand raiseCube;
	private SharedCommand clampCube;
	private SharedCommand lowerCube;
	private SharedCommand cubeFinesse;
	private SharedCommand acquireCubeExchange;

	public OI() {
		// Setup driver joystick
		try {
			driverXbox = new Joystick(DRIVER_XBOX);
			dButtonA = new JoystickButton(driverXbox, buttonAid);
			dButtonB = new JoystickButton(driverXbox, buttonBid);
			dButtonX = new JoystickButton(driverXbox, buttonXid);
			dButtonY = new JoystickButton(driverXbox, buttonYid);
			dButtonBumpL = new JoystickButton(driverXbox, buttonBumpLid);
			dButtonBumpR = new JoystickButton(driverXbox, buttonBumpRid);
			dButtonBack = new JoystickButton(driverXbox, buttonBackid);
			dButtonStart = new JoystickButton(driverXbox, buttonStartid);
			dButtonLClick = new JoystickButton(driverXbox, buttonLClickid);
			dButtonRClick = new JoystickButton(driverXbox, buttonRClickid);
			dPOV0 = new POV(driverXbox, POV_0);
			dPOV90 = new POV(driverXbox, POV_90);
			dPOV135 = new POV(driverXbox, POV_135);
			dPOV180 = new POV(driverXbox, POV_180);
			dPOV225 = new POV(driverXbox, POV_225);
			dPOV270 = new POV(driverXbox, POV_270);
			dPOV315 = new POV(driverXbox, POV_315);
		} catch (Exception e) {
			Robot.m_logger.console("OI: Unable to setup driver joystick: " + e.toString());
		}

		try {
			operatorXbox = new Joystick(OPERATOR_XBOX);
			oButtonA = new JoystickButton(operatorXbox, buttonAid);
			oButtonB = new JoystickButton(operatorXbox, buttonBid);
			oButtonX = new JoystickButton(operatorXbox, buttonXid);
			oButtonY = new JoystickButton(operatorXbox, buttonYid);
			oButtonBumpL = new JoystickButton(operatorXbox, buttonBumpLid);
			oButtonBumpR = new JoystickButton(operatorXbox, buttonBumpRid);
			oButtonBack = new JoystickButton(operatorXbox, buttonBackid);
			oButtonStart = new JoystickButton(operatorXbox, buttonStartid);
			oButtonLClick = new JoystickButton(operatorXbox, buttonLClickid);
			oButtonRClick = new JoystickButton(operatorXbox, buttonRClickid);
			oPOV0 = new POV(operatorXbox, POV_0);
			oPOV90 = new POV(operatorXbox, POV_90);
			oPOV135 = new POV(operatorXbox, POV_135);
			oPOV180 = new POV(operatorXbox, POV_180);
			oPOV225 = new POV(operatorXbox, POV_225);
			oPOV270 = new POV(operatorXbox, POV_270);
			oPOV315 = new POV(operatorXbox, POV_315);
		} catch (Exception e) {
			Robot.m_logger.console("OI: Unable to setup operator joystick: " + e.toString());
		}
		ControllerMap.setControllers(driverXbox, operatorXbox);
		setupOperatorButtons();
		setupDriverXboxButtons();

		try {
			expelCube = new SharedCommand(driverXbox, ControllerMap.expelCubeDriver, false, operatorXbox,
					ControllerMap.expelCubeOperator, false);
			acquireCube = new SharedCommand(driverXbox, ControllerMap.acquireCubeDriver, false, operatorXbox,
					ControllerMap.acquireCubeOperator, false);
			raiseCube = new SharedCommand(driverXbox, ControllerMap.raiseCubeDriver, false, operatorXbox,
					ControllerMap.raiseCubeOperator, false);
			clampCube = new SharedCommand(driverXbox, ControllerMap.clampCubeDriver, false, operatorXbox,
					ControllerMap.clampCubeOperator, false);
			lowerCube = new SharedCommand(driverXbox, ControllerMap.lowerCubeDriver, false, operatorXbox,
					ControllerMap.lowerCubeOperator, false);
			cubeFinesse = new SharedCommand(driverXbox, ControllerMap.cubeFinesseDriver, true, operatorXbox,
					ControllerMap.cubeFinesseOperator, false);
			acquireCubeExchange = new SharedCommand(driverXbox, ControllerMap.acquireCubeExchangeDriver, true,
					operatorXbox, ControllerMap.acquireCubeExchangeOperator, false);
			setupSharedCommands();
		} catch (Exception e) {
			Robot.m_logger.console("OI: Unable to setup shared commands: " + e.toString());
		}
	}

	public void setupOperatorButtons() {
		oPOV0.whenReleased(new ElevatorMoveHigh(0.75, 10));
		oPOV180.whenReleased(new ElevatorMoveLow(0.25, 10));
		oButtonBumpR.whenPressed(new ClimberMove(0.25, ControllerMap.climberMoveJoystick, ControllerMap.climberMoveOperator));
	}

	public void setupDriverXboxButtons() {
		dButtonStart.whenReleased(new ElevatorMoveTo(50, 0.5));
		dButtonBack.whenReleased(new NavigateToCube(0.25, 2, 2, 0.25, 4, 15, 0, 27, 5));
	}

	public void setupSharedCommands() {
		expelCube.whenPressed(new CubeExpel(1, ControllerMap.expelCubeDriver, ControllerMap.expelCubeOperator,
				operatorXbox, driverXbox));
		acquireCube.whenReleased(new AcquireCube());
		raiseCube.whenReleased(new AutoCubeMoveHigh());
		clampCube.whenReleased(new CubeSolenoidToggle());
		lowerCube.whenReleased(new AutoCubeMoveLow());
		cubeFinesse.whenReleased(new CubeFinesse(.25));
		acquireCubeExchange.whenReleased(new AcquireCubeExchange());
	}

}
