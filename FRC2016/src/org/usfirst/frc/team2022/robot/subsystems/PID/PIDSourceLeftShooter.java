package org.usfirst.frc.team2022.robot.subsystems.PID;

import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.PIDSource;

public class PIDSourceLeftShooter implements PIDSource{

	ShooterSubsystem shooterSubsystem = Robot.shooterSubsystem;
	
	@Override
	public double pidGet() {
		return shooterSubsystem.getLeftEncoderRate();
	}

}
