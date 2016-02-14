package org.usfirst.frc.team2022.robot.commands;

import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.subsystems.Intakes;
import org.usfirst.frc.team2022.robot.subsystems.ShooterPositions;
import org.usfirst.frc.team2022.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterCommand extends Command {
	//Create reference to shooter subsystem
	ShooterSubsystem shooterSubsystem;
	//Create reference to OI
	OI oi;

    public ShooterCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooterSubsystem);
    	shooterSubsystem = Robot.shooterSubsystem;
    	
    	oi = Robot.oi;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(oi.attack3.getButton(1)){
    		shooterSubsystem.setShooterAngle(ShooterPositions.down);
    	}
    	else if(oi.attack3.getButton(2)){
    		shooterSubsystem.setShooterAngle(ShooterPositions.flat);
    	}
    	else if(oi.attack3.getButton(3)){
    		shooterSubsystem.setShooterAngle(ShooterPositions.up);
    	}
    	
    	if(oi.attack3.getButton(4)){
    		shooterSubsystem.setIntake(Intakes.in);
    	}
    	else if(oi.attack3.getButton(5)){
    		shooterSubsystem.setIntake(Intakes.out);
    	}
    	else{
    		shooterSubsystem.setIntake(Intakes.neutral);
    	}
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooterSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
