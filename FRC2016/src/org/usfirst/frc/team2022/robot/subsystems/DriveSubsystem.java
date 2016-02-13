package org.usfirst.frc.team2022.robot.subsystems;

import org.usfirst.frc.team2022.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// Creates TalonSR motor controller objects for
	// the left and right motor controllers.
	private TalonSRX left, right;
	private boolean inverted;
	private Encoder leftEncoder, rightEncoder;
	
	// Constructor initializes these variables.
	public DriveSubsystem() {
		
		this.left = new TalonSRX(RobotMap.leftDrivePort);
		this.right = new TalonSRX(RobotMap.rightDrivePort);
		this.inverted = false;
		this.leftEncoder = new Encoder(RobotMap.leftEncoderA, RobotMap.leftEncoderB, false);
		this.rightEncoder = new Encoder(RobotMap.rightEncoderA, RobotMap.rightEncoderB, false);
		
	}
	
	// Setter methods for each side.
	private void setLeftSpeed(double speed) {
		
		left.set(speed);
		
	}
	
	private void setRightSpeed (double speed) {
		
		right.set(speed);
		
	}
	
	// Getter methods for each side.
	public double getLeftSpeed() {
		
		return left.getSpeed();
		
	}
	
	public double getRightSpeed() {
		
		return right.getSpeed();
		
	}
	
	// Encoder getters.
	public double getLeftEncoderDistance() {
		
		return leftEncoder.getDistance();
		
	}
	
	public double getRightEncoderDistance() {
		
		return rightEncoder.getDistance();
		
	}
	
	// Setter and Getter for inverted.
	private boolean isInverted() {
		
		return inverted;
		
	}
	
	private void toggleInversion() {
		
		inverted = !inverted;
		
	}
	
	public void stop() {
		
		this.left.set(0);
		this.right.set(0);
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

