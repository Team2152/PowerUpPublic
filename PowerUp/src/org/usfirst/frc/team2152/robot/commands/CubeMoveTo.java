//package org.usfirst.frc.team2152.robot.commands;
//
//import org.usfirst.frc.team2152.robot.Robot;
//
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class CubeMoveTo extends Command {
//
//	double cubeRaiseSpeed;
//	double cubeLowerSpeed;
//	int cubeLeftTriggerid  = 2;
//	int cubeRightTriggerid = 3;
//	Joystick joystick;
//	
//	
//	
//	
//    public CubeMoveTo(double cubeRaiseSpeed, double cubeLowerSpeed, Joystick joystick ) {
//        // Use requires() here to declare subsystem dependencies
//        // eg. requires(chassis);
//    	requires(Robot.cubeMoveSubsystem);
//    	this.cubeRaiseSpeed     = cubeRaiseSpeed;
//    	this.cubeLowerSpeed     = cubeLowerSpeed;
//
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	if(joystick.getRawAxis(cubeLeftTriggerid) == .1){
//    		Robot.cubeMoveSubsystem.setCubeRaiseSpeed(cubeRaiseSpeed);
//    		
//    	}
//    	else if(joystick.getRawAxis(cubeRightTriggerid) == .1){
//    		Robot.cubeMoveSubsystem.setCubeLowerSpeed(cubeLowerSpeed);
//    	}
//    	
//    	if(joystick.getRawAxis(cubeLeftTriggerid) == 0 && joystick.getRawAxis(cubeRightTriggerid) == 0 ){
//    		Robot.cubeMoveSubsystem.setCubeRaiseSpeed(cubeRaiseSpeed);
//    		Robot.cubeMoveSubsystem.setCubeLowerSpeed(cubeLowerSpeed);
//    	}
//    	
//    	
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return false;
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    }
//}
