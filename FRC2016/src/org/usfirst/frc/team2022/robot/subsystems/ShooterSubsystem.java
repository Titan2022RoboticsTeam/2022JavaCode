package org.usfirst.frc.team2022.robot.subsystems;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.RobotMap;
import org.usfirst.frc.team2022.robot.commands.ShooterCommand;
import org.usfirst.frc.team2022.robot.subsystems.PID.PIDOutputLeft;
import org.usfirst.frc.team2022.robot.subsystems.PID.PIDOutputRight;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {
    
    // names for motor controller objects
	TalonSRX intakeLeft, intakeRight, hinge;
	
	//names for wheel encoders
	Encoder rightEncoder, leftEncoder;
	
	//names for PID Controllers
	PIDController rightController;
	PIDController leftController;
	
	//names for PID Output
	PIDOutputRight pidOutputRight;
	PIDOutputLeft pidOutputLeft;
	
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
		
		//Instantiate Encoders
		rightEncoder = new Encoder(RobotMap.rightShooterEncoderPortA, RobotMap.rightShooterEncoderPortB, false);
		leftEncoder = new Encoder(RobotMap.leftShooterEncoderPortA, RobotMap.leftShooterEncoderPortB, false);
		
		//Set distance per pulse for encoders
		rightEncoder.setDistancePerPulse(ConstantsMap.SHOOTER_ENCODER_DIST_PER_TICK);
		leftEncoder.setDistancePerPulse(ConstantsMap.SHOOTER_ENCODER_DIST_PER_TICK);
		
		// sets that the PID controllers are going to get the speeds of the motors
		rightEncoder.setPIDSourceType(PIDSourceType.kRate);
		leftEncoder.setPIDSourceType(PIDSourceType.kRate);
		
		//Instantiate PID controllers and output objects
		pidOutputRight = new PIDOutputRight();
		rightController = new PIDController(ConstantsMap.pShooter, ConstantsMap.iShooter, ConstantsMap.dShooter, ConstantsMap.fShooter, rightEncoder, pidOutputRight);

		pidOutputLeft = new PIDOutputLeft();
		leftController = new PIDController(ConstantsMap.pShooter, ConstantsMap.iShooter, ConstantsMap.dShooter, ConstantsMap.fShooter, leftEncoder, pidOutputLeft);
		
		//Set Ouput Range for pid outputs
		rightController.setOutputRange(-1, 1);
		leftController.setOutputRange(-1,1);
		
		//Set the tolerance for on target method
		rightController.setAbsoluteTolerance(0.1);
		leftController.setAbsoluteTolerance(0.1);
		
		
	}
	
	public void setIntake (Intakes i) {
		
		switch(i) {
			case in :
				if(ballIsInChannel() == false){
					intakeLeft.set(-0.5);
					intakeRight.set(-0.5);
				}
				else{
					intakeLeft.set(0);
					intakeRight.set(0);
				}
				break;
			case neutral :
				intakeLeft.set(0);
				intakeRight.set(0);
			case out :
				resetEncoders();
				enableRightPIDController(ConstantsMap.SHOOTER_SPEED);
				enableLeftPIDController(ConstantsMap.SHOOTER_SPEED);
				while(rightController.onTarget() == false && leftController.onTarget() == false){
					intakeLeft.set(pidOutputLeft.getOutput());
					intakeRight.set(pidOutputRight.getOutput());
				}
				setShooterAngle(ShooterPositions.shoot);
				Timer.delay(1000);
				//Then write code for kicker
				disablePIDControllers();
				intakeLeft.set(0);
				intakeRight.set(0);
				
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
			case ready:
				pos = ConstantsMap.READY_POSITION_ANGLE;
				break;
			case shoot:
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
		
		hinge.set(0);
			
	}
	
	public boolean ballIsInChannel() {
		if (ballLimitSwitch.get()){
			return true;
		} else {
			return false;
		}
	}
	
	//Get Encoder Distances
	public double getRightEncoderDistance(){
		return rightEncoder.getDistance();
	}
	
	public double getLeftEncoderDistance(){
		return leftEncoder.getDistance();
	}
	//Get raw encoder counts
	public int getLeftEncoderRawValue(){
		return leftEncoder.get();
	}
	
	public int getRightEncoderRawValue(){
		return rightEncoder.get();
	}
	//Get Encoder Rates
	public double getRightEncoderRate(){
		return rightEncoder.getRate();
	}
	
	public double getLeftEncoderRate(){
		return leftEncoder.getRate();
	}
	
	//reset encoders
	public void resetEncoders(){
		rightEncoder.reset();
		leftEncoder.reset();
	}
	
	//PID Methods
	public void enableRightPIDController(double speed){
		rightController.setSetpoint(speed);
		rightController.enable();
	}
	public void disablePIDControllers(){
		rightController.disable();
		leftController.disable();
	}
	
	public void enableLeftPIDController(double speed){
		leftController.setSetpoint(speed);
		leftController.enable();
	}
	
	public double getRightPIDOutput(){
		return pidOutputRight.getOutput();
	}
	
	public double getLeftPIDOuput(){
		return pidOutputLeft.getOutput();
	}

	//Is the robot within the absolute tolerance of the target distance
	public boolean rightPIDOnTarget(){
		return rightController.onTarget();
	}
	
	public boolean leftPIDOnTarget(){
		return leftController.onTarget();
	}
	
	public void stop(){
		intakeLeft.set(0);
		intakeRight.set(0);
		hinge.set(0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ShooterCommand());
    }
}

