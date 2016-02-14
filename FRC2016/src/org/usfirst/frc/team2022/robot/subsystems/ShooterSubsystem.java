package org.usfirst.frc.team2022.robot.subsystems;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {
    
    // names for motor controller objects
	TalonSRX intakeLeft, intakeRight, hinge;
	
	// names for sensor objects
	DigitalInput ballLimitSwitch;
	AnalogPotentiometer pot;
	
	// names for double variables
	double currentAngle, zeroAngleOffset;
	
	// double variable for position of shooter
	double pos;
	
	public ShooterSubsystem() {
		
		// initiates motors
		intakeLeft = new TalonSRX(RobotMap.frontIntakePort);
		intakeRight = new TalonSRX(RobotMap.backIntakePort);
		hinge = new TalonSRX(RobotMap.hingePort);
		
		// initiates sensors
		ballLimitSwitch = new DigitalInput(RobotMap.ballLimitSwitchPort);
		pot = new AnalogPotentiometer(RobotMap.potentiometerPort, 360, 0);
		
	}
	
	public void setIntake (Intakes i) {
		
		switch(i) {
			case in :
				intakeLeft.set(-1);
				intakeRight.set(-1);
				break;
			case neutral :
				intakeLeft.set(0);
				intakeRight.set(0);
			case out :
				intakeLeft.set(1);
				intakeRight.set(1);
		}
		
	}

	public void setShooterAngle(ShooterPositions p) {
		
		switch (p) {
			case down:
				pos = ConstantsMap.DOWN_POSITION_ANGLE;
				break;
			case flat:
				pos = ConstantsMap.FLAT_POSITION_ANGLE;
				break;
			case up:
				pos = ConstantsMap.UP_POSITION_ANGLE;
				break;
		}
		
		if (pot.get() < pos) {
			
			while (pot.get() < pos) {
				
				hinge.set(.8);
				
			}
			
		} else if (pot.get() > pos) {
			while (pot.get() > pos) {
					
				hinge.set(-.8);
					
			}
				
		}
			
	}
	
	public boolean ballIsInChannel() {
		if (ballLimitSwitch.get()){
			return true;
		} else {
			return false;
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

