package org.usfirst.frc.team2152.robot.auto;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.commands.AutoCruise;
import org.usfirst.frc.team2152.robot.commands.AutoRamp;
import org.usfirst.frc.team2152.robot.commands.AutoStop;
import org.usfirst.frc.team2152.robot.commands.ClearDriveBackLash;
import org.usfirst.frc.team2152.robot.commands.CubeExpelSensor;
import org.usfirst.frc.team2152.robot.commands.CubeExpelTime;
import org.usfirst.frc.team2152.robot.commands.Delay;
import org.usfirst.frc.team2152.robot.commands.MoveByEncoder;
import org.usfirst.frc.team2152.robot.commands.PreCannedTurn;
import org.usfirst.frc.team2152.robot.commands.SetCubeIntake;
import org.usfirst.frc.team2152.robot.utilities.PIDConstants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SwitchCenter extends CommandGroup {

    public SwitchCenter() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	requires(Robot.driveTrainSubsystem);
    	requires(Robot.cubeIntakeSubsystem);
    	Timer.delay(SmartDashboard.getNumber("Autonomous Delay", 0));
    	String switchPosition = Robot.powerUpDashboard.getPlateAssignment("Switch Plates");
    	
    	addParallel(new ClearDriveBackLash());
    	Robot.cubeIntakeSubsystem.cubeSolenoidClose();
    	if (switchPosition.equals("Left")){
    		
    		// Navigate to left switch plate
	    	
        	addSequential(new AutoRamp(.4, .15, 1, 75));
        	addSequential(new AutoRamp(.4, -.2, 1, 75));
        	addSequential(new AutoStop());

    		
    		
//    		addSequential(new MoveByEncoder(53,53,PIDConstants.ENCODER_DRIVE_SPEED,false));
//	    		addSequential(new PreCannedTurn(-90,false));
//	    		addSequential(new MoveByEncoder(74,74,PIDConstants.ENCODER_DRIVE_SPEED,false));
//	    		addSequential(new PreCannedTurn(90,false));
//	    		addSequential(new MoveByEncoder(85,85,PIDConstants.ENCODER_DRIVE_SPEED,false));
    		
    		// Cube Delivery
    		addSequential(new CubeExpelTime(.75,1));
    		
    	} else if (switchPosition.equals("Right")){
    		
    		// Navigate to right switch plate

    		addSequential(new AutoRamp(.4, -.15, 1, 55));
    		addSequential(new AutoRamp(.4, 0, 1, 17));
        	addSequential(new AutoRamp(.4, .2, 1, 55));
        	addSequential(new AutoStop());
    		
    		
    		//	    		addSequential(new MoveByEncoder(53,53,PIDConstants.ENCODER_DRIVE_SPEED,false));
//	    		addSequential(new PreCannedTurn(45,false));
//	    		addSequential(new MoveByEncoder(46,46,PIDConstants.ENCODER_DRIVE_SPEED,false));
//	    		addSequential(new PreCannedTurn(-45,false));
//	    		addSequential(new MoveByEncoder(51,51,PIDConstants.ENCODER_DRIVE_SPEED,false));
//    		
    		// Cube Delivery
	    		
    		addSequential(new CubeExpelTime(.75,1));
    	
    	} else {
    		// Only Cross Baseline
    		
    		addSequential(new AutoRamp(.75, -.25, 1, 100));
    		addSequential(new AutoStop());
    		//addSequential(new AutoRamp(0, .25, 1, 30, .75));
    		
//	    		addSequential(new MoveByEncoder(53,53,PIDConstants.ENCODER_DRIVE_SPEED,false));
//	    		addSequential(new PreCannedTurn(45,false));
//	    		addSequential(new MoveByEncoder(46,46,PIDConstants.ENCODER_DRIVE_SPEED,false));
//	    		addSequential(new PreCannedTurn(-45,false));
//	    		addSequential(new MoveByEncoder(51,51,PIDConstants.ENCODER_DRIVE_SPEED,false));
    	}
    }
}
