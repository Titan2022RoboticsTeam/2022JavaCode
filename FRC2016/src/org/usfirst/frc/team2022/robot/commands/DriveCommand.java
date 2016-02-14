package org.usfirst.frc.team2022.robot.commands;

import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.controllers.Attack3;
import org.usfirst.frc.team2022.robot.controllers.Xbox;
import org.usfirst.frc.team2022.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCommand extends Command {
	
	// Names DriveSubsystem object.
	DriveSubsystem driveSubsystem;
	// Names OI object.
	OI oi;
	// Names boolean variable for whether the controls are inverted.
	private boolean inverted;
	
    public DriveCommand() {
    	// Initializes the variable driveSubsystem.
    	driveSubsystem = Robot.driveSubsystem;
    	oi = Robot.oi;
    	
        // Requires method tells which subsystem it uses.
    	requires(driveSubsystem);
    	inverted = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double speedModifier = .75;
    	double right = Math.max(Math.min(oi.xbox.GetRightY(), 1), -1);
    	double left = Math.max(Math.min(oi.xbox.GetLeftY(), 1), -1);
    		
    	if (oi.xbox.GetRightBumperValue()) {
    		driveSubsystem.toggleInversion();
    	}
    		
//    	if(driveSubsystem.isInverted()){
//    		speedModifier = .3;
//    	}
    		
    	if (oi.xbox.GetLeftTriggers() > 0.1) { // turtle
    		speedModifier = .5; 
    	} else if (oi.xbox.GetRightTriggers() > 0.1) { // turbo
    		speedModifier = 1; 
    	}
    	
    	if(driveSubsystem.isInverted()){
    		driveSubsystem.setLeftSpeed(right * speedModifier);
    		driveSubsystem.setRightSpeed(left * speedModifier);
    	}else{
    		driveSubsystem.setLeftSpeed(left * speedModifier);
    		driveSubsystem.setRightSpeed(right * speedModifier);
    	}
    	    }

   	// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
   		return false;
   	}

   	// Called once after isFinished returns true
   	protected void end() {
   		
   		driveSubsystem.stop();
   		
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
