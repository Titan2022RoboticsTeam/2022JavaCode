
package org.usfirst.frc.team2022.robot;

import org.usfirst.frc.team2022.robot.commands.DriveCommand;
import org.usfirst.frc.team2022.robot.commands.autonomous.groups.DefaultAutonomousCommandGroup;
import org.usfirst.frc.team2022.robot.commands.autonomous.groups.LowBarHighGoalAutonomousCommandGroup;
import org.usfirst.frc.team2022.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
	//Instantiate Subsystems
	public static final DriveSubsystem driveSubsystem = new DriveSubsystem();
	
	//Create reference to OI
	public static OI oi;
	
	//Create References to commands
	public DriveCommand driveCommand;
	
	SendableChooser autoChooser;
	
	CommandGroup autonomousCommand;
	
	
    public void robotInit() {
    	//Instantiate OI
    	oi = new OI();
    	//Instantiate Commands
    	driveCommand = new DriveCommand();
    	autoChooser.addDefault("Default Command", new DefaultAutonomousCommandGroup());
    	autoChooser.addObject("Low Bar High Goal Command", new LowBarHighGoalAutonomousCommandGroup());
    	SmartDashboard.putData("Autonomous Mode Chooser", autoChooser);
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autonomousCommand = (CommandGroup) autoChooser.getSelected();
    	autonomousCommand.start();
    }


	@Override
	public void teleopInit() {
		driveCommand.start();
	}

    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
