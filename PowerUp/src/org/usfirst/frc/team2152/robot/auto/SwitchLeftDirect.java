package org.usfirst.frc.team2152.robot.auto;

import org.usfirst.frc.team2152.robot.Robot;
import org.usfirst.frc.team2152.robot.commands.AutoRamp;
import org.usfirst.frc.team2152.robot.commands.AutoStop;
import org.usfirst.frc.team2152.robot.commands.CubeExpelTime;
import org.usfirst.frc.team2152.robot.commands.Delay;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SwitchLeftDirect extends CommandGroup {

    public SwitchLeftDirect() {
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
    	requires(Robot.cubeIntakeSubsystem);
    	requires(Robot.driveTrainSubsystem);
    	Timer.delay(SmartDashboard.getNumber("Autonomous Delay", 0));
    	String switchPosition = Robot.powerUpDashboard.getPlateAssignment("Switch Plates");
    	
		//addSequential(new ClearDriveBackLash());
		addSequential(new Delay(0.25));
		//addSequential(new ResetDriveEncoders());
    	if (switchPosition.equals("Left")){
    		//Navigate to Switch
    		addSequential(new AutoRamp(.4, -.15, 1, 25));
    		addSequential(new AutoRamp(.4, 0, 1, 17));
        	addSequential(new AutoRamp(.4, .2, 1, 25));
    		addSequential(new AutoRamp(.4, 0, 1, 42));
        	addSequential(new AutoStop());

        	
    		//Cube Delivery
    		addSequential(new CubeExpelTime(.75,1));
    	} else if (switchPosition.equals("Right")){
    		//Only Cross Baseline
    		addSequential(new AutoRamp(.4, -.15, 1, 25));
    		addSequential(new AutoRamp(.4, 0, 1, 17));
        	addSequential(new AutoRamp(.4, .2, 1, 25));
    		addSequential(new AutoRamp(.4, 0, 1, 40));
        	addSequential(new AutoStop());
 
    	} else {
    		//Only Cross Baseline
				//addSequential(new MoveByEncoder(104,104,PIDConstants.ENCODER_DRIVE_SPEED, true ,3.5));
            	addSequential(new AutoRamp(.4, 0, .5, 104));

    	}
    }
}
