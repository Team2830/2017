// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2830.PeligrosoRobot.commands;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.usfirst.frc2830.PeligrosoRobot.Robot;
import org.usfirst.frc2830.PeligrosoRobot.util.directions.DrivingDirections;

/**
 *
 */
public class AutonomousCommand extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	Properties directions;
	DrivingDirections drivingDirections;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public AutonomousCommand() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	resetCounters();
    	directions = new Properties();
        String fileName = "app.config";
        InputStream is;
		try {
			is = new FileInputStream(fileName);        
			directions.load(is);
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Autonomous");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException Autonomous");
			e.printStackTrace();
		}
		drivingDirections = new DrivingDirections(directions);
		

    }

	private void resetCounters() {
		Robot.drivetrain.getLeftEncoder().reset();
    	Robot.drivetrain.getRightEncoder().reset();
    	Robot.drivetrain.getAnalogGyro1().reset();
	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.driveForward(.7,0);
    	
    	if(Robot.drivetrain.getAnalogGyro1().getAngle()>1.00){
    		Robot.drivetrain.driveForward(.7,.5);
    	}
    	if(Robot.drivetrain.getAnalogGyro1().getAngle()<-1.00){
    		Robot.drivetrain.driveForward(.7,-.5);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(100.00 <= Robot.drivetrain.getLeftEncoder().getDistance() || 100.00 <= Robot.drivetrain.getRightEncoder().getDistance()){
        	return true;
        }
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.driveForward(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.driveForward(0, 0);
    }
}
