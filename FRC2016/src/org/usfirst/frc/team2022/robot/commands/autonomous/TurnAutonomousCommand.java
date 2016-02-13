package org.usfirst.frc.team2022.robot.commands.autonomous;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnAutonomousCommand extends Command {
	DriveSubsystem tankDriveSubsystem;
	double angle;
	boolean running = true;
	double rightDistance;
	double leftDistance;


    public TurnAutonomousCommand(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	tankDriveSubsystem = Robot.driveSubsystem;
    	this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Calculate how far each side has to move
    	double circumfrence = ConstantsMap.robotWidth*Math.PI;
    	double distance = (angle/360)*circumfrence;
    	
    	if(angle > 0){
    		rightDistance = -distance;
    		leftDistance = distance;
    	}
    	else{
    		rightDistance = distance;
    		leftDistance = -distance;
    	}
    	
    	tankDriveSubsystem.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Start PID Controllers
    	tankDriveSubsystem.enableRightPIDController(rightDistance);
    	tankDriveSubsystem.enableLeftPIDController(leftDistance);
    	
    	//While robot hasn't reached target
    	while(tankDriveSubsystem.rightPIDOnTarget() == false && tankDriveSubsystem.leftPIDOnTarget() == false){
			tankDriveSubsystem.setRightSpeed(tankDriveSubsystem.getRightPIDOutput());
			tankDriveSubsystem.setLeftSpeed(tankDriveSubsystem.getLeftPIDOuput());
    	}
    	
    	long time = System.currentTimeMillis();
		tankDriveSubsystem.disablePIDControllers();
	    tankDriveSubsystem.setRightSpeed(0);
	    tankDriveSubsystem.setLeftSpeed(0);
    	while(System.currentTimeMillis() < time + 1000){
    		
    	}
	    tankDriveSubsystem.resetEncoders();
	    running = false;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !running;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
