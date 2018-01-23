package org.usfirst.frc.team2152.robot.subsystems;

import org.usfirst.frc.team2152.robot.RobotMap;
import org.usfirst.frc.team2152.robot.commands.TankDriveJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.


	// === Steamworks Speed Controllers
	private WPI_TalonSRX right1;
	private WPI_TalonSRX right2;
	private WPI_TalonSRX right3;
	private WPI_TalonSRX left1;
	private WPI_TalonSRX left2;
	private WPI_TalonSRX left3;

	// === Drive Train Object
	private DifferentialDrive drive;

	public DriveTrain() {

		// Steamworks Motor Controllers
		// Create TalonSRX Objects for each of the motors
		right1 = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_1_CAN_Id);
		right1.setNeutralMode(NeutralMode.Brake);

		right2 = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_2_CAN_Id);
		right2.setNeutralMode(NeutralMode.Brake);
		right2.set(ControlMode.Follower,RobotMap.RIGHT_DRIVE_1_CAN_Id);

		right3 = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_3_CAN_Id);
		right3.setNeutralMode(NeutralMode.Brake);
		right3.set(ControlMode.Follower,RobotMap.RIGHT_DRIVE_1_CAN_Id);

		left1 = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_1_CAN_Id);
		left1.setNeutralMode(NeutralMode.Brake);

		left2 = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_2_CAN_Id);
		left2.setNeutralMode(NeutralMode.Brake);
		left2.set(ControlMode.Follower,RobotMap.LEFT_DRIVE_1_CAN_Id);

		left3 = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_3_CAN_Id);
		left3.setNeutralMode(NeutralMode.Brake);
		left3.set(ControlMode.Follower,RobotMap.LEFT_DRIVE_1_CAN_Id);

		drive = new DifferentialDrive(left1,right1);
		drive.setSafetyEnabled(true);

	}

	/***
	 * 
	 * Move motors using Tank Drive
	 * @param leftSpeed
	 * 	The speed of the left motor
	 * @param rightSpeed
	 * 	The speed of the right motor
	 */
	public void tankDrive(double leftSpeed, double rightSpeed) {
		drive.tankDrive(leftSpeed, rightSpeed);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new TankDriveJoystick());
	}
}

