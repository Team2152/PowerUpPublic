package org.usfirst.frc.team2152.robot.commands;

import org.usfirst.frc.team2152.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class AutoCubeMoveHigh extends ConditionalCommand {

	public AutoCubeMoveHigh() {
		super(new AutoCubeMoveHighWithCube(), new AutoCubeMoveHighNoCube());
	}

	@Override
	protected boolean condition() {
		if (Robot.cubeIntakeSubsystem.cubeDetectIn() || Robot.cubeIntakeSubsystem.cubeDetectOutLeft()
				|| Robot.cubeIntakeSubsystem.cubeDetectOutRight()){
			return true;
		} else {
			return false;
		}
	}
	
	
}
