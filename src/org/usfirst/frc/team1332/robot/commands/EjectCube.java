package org.usfirst.frc.team1332.robot.commands;

import org.usfirst.frc.team1332.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EjectCube extends Command {

    public EjectCube() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.robot.ss_pickup.runPickup(-1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
