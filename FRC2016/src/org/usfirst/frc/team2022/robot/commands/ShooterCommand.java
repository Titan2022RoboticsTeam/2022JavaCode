package org.usfirst.frc.team2022.robot.commands;

import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.commands.autonomous.DriveToShootingRange;
import org.usfirst.frc.team2022.robot.commands.autonomous.ShootAutonomousCommand;
import org.usfirst.frc.team2022.robot.commands.autonomous.TurnCameraAutonomousCommand;
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
	long lastShot = 0;
	TurnCameraAutonomousCommand turnCameraAutonomousCommand= new TurnCameraAutonomousCommand();
	DriveToShootingRange driveToShootingRange = new DriveToShootingRange();
	ShootAutonomousCommand shootAutonomousCommand = new ShootAutonomousCommand();

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
    	
    	//Intake Buttons
    	if(oi.xbox.GetLeftTriggers() > 0.1){
    		shooterSubsystem.setIntake(Intakes.in);
    	}
    	else if(oi.xbox.GetRightTriggers() > 0.1){
    		if(lastShot < System.currentTimeMillis() - 1000){
    			boolean finished = false;
    			while(oi.xbox.GetLeftBumperValue() == false || finished == false){
    				//Automatically Shoot
        			turnCameraAutonomousCommand.start();
        			while(turnCameraAutonomousCommand.isRunning()){
        				
        			}
        			driveToShootingRange.start();
        			while(driveToShootingRange.isRunning()){
        				
        			}
        			shootAutonomousCommand.start();
        			while(shootAutonomousCommand.isRunning()){
        				
        			}
        			lastShot = System.currentTimeMillis();
        			finished = true;
    			}
    			turnCameraAutonomousCommand.cancel();
    			driveToShootingRange.cancel();
    			shootAutonomousCommand.cancel();
    		}
    	}
    	else if(oi.xbox.GetRightBumperValue()){
    		new ShootAutonomousCommand();
    	}
    	else{
    		shooterSubsystem.setIntake(Intakes.neutral);
    	}
    	
    	//Set Shooter Angle Buttons
    	if(oi.xbox.GetAValue()){
    		shooterSubsystem.setShooterAngle(ShooterPositions.down);
    	}
    	else if(oi.xbox.GetBValue()){
    		shooterSubsystem.setShooterAngle(ShooterPositions.flat);
    	}
    	else if(oi.xbox.GetYValue()){
    		shooterSubsystem.setShooterAngle(ShooterPositions.ready);
    	} else if (oi.xbox.GetXValue()) {
    		shooterSubsystem.setShooterAngle(ShooterPositions.shoot);
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
