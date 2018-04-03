package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.auto.BaselineCenter;
import org.usfirst.frc.team2152.robot.auto.BaselineLeft;
import org.usfirst.frc.team2152.robot.auto.BaselineRight;
import org.usfirst.frc.team2152.robot.auto.ScaleLeft;
import org.usfirst.frc.team2152.robot.auto.ScaleLeftDirect;
import org.usfirst.frc.team2152.robot.auto.ScaleRight;
import org.usfirst.frc.team2152.robot.auto.ScaleRightDirect;
import org.usfirst.frc.team2152.robot.auto.SwitchCenter;
import org.usfirst.frc.team2152.robot.auto.SwitchLeft;
import org.usfirst.frc.team2152.robot.auto.SwitchLeftDirect;
import org.usfirst.frc.team2152.robot.auto.SwitchRight;
import org.usfirst.frc.team2152.robot.auto.SwitchRightDirect;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RunAutoInTele extends Command {
	private Command baseLineLeft;
	private Command baseLineRight;
	private Command baseLineCenter;
	private Command switchLeft;
	private Command switchLeftStraight;
	private Command switchRight;
	private Command switchRightStraight;
	private Command switchCenter;
	private Command scaleLeft;
	private Command scaleLeftStraight;
	private Command scaleRight;
	private Command scaleRightStraight;
	
	private Command commandToRun;
	
	/**
	 * Runs autonomous commands in Teleop
	 * <br>Uses SmartDashboard value "Teleop Auto Id" to determine which auto to run
	 * <br>SmartDashboard value should be an int between 1 and 12 inclusive
	 * <br>Commands are in the same order as the commands on the chooser for normal autonomous
	 */
    public RunAutoInTele() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	baseLineLeft = new BaselineLeft();
    	baseLineRight = new BaselineRight();
    	baseLineCenter = new BaselineCenter();
    	switchLeft = new SwitchLeft();
    	switchLeftStraight = new SwitchLeftDirect();
    	switchRight = new SwitchRight();
    	switchRightStraight = new SwitchRightDirect();
    	switchCenter = new SwitchCenter();
    	scaleLeft = new ScaleLeft();
    	scaleLeftStraight = new ScaleLeftDirect();
    	scaleRight = new ScaleRight();
    	scaleRightStraight = new ScaleRightDirect();
    	
    	switch((int) SmartDashboard.getNumber("Teleop Auto Id", 0)) {
    	case(1):
    		commandToRun = baseLineLeft;
    		break;
    	case(2):
    		commandToRun = baseLineRight;
    		break;
    	case(3):
    		commandToRun = baseLineCenter;
    		break;
    	case(4):
    		commandToRun = switchLeft;
    		break;
    	case(5):
    		commandToRun = switchLeftStraight;
    		break;
    	case(6):
    		commandToRun = switchRight;
    		break;
    	case(7):
    		commandToRun = switchRightStraight;
    		break;
    	case(8):
    		commandToRun = switchCenter;
    		break;
    	case(9):
    		commandToRun = scaleLeft;
    		break;
    	case(10):
    		commandToRun = scaleLeftStraight;
    		break;
    	case(11):
    		commandToRun = scaleRight;
    		break;
    	case(12):
    		commandToRun = scaleRightStraight;
    	default:
    		commandToRun = null;
    		break;
    	}
    	
    	if (commandToRun != null) {
    		System.out.println("Teleop Auto Command Started");
    		commandToRun.start();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(commandToRun == null || commandToRun.isCompleted()) {
    		System.out.println("Teleop Auto Ended");
    		return true;
    	} else {
        return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
