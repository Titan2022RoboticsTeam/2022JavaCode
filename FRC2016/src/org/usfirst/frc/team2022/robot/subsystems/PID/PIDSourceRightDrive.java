package org.usfirst.frc.team2022.robot.subsystems.PID;

import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.PIDSource;

public class PIDSourceRightDrive implements PIDSource{

	DriveSubsystem driveSubsystem = Robot.driveSubsystem;
	
	@Override
	public double pidGet() {
		return driveSubsystem.getRightEncoderDistance();
	}

}
