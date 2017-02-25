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
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.internal.HardwareTimer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc2830.PeligrosoRobot.Robot;

/**
 *
 */
public class Turn extends Command {
	private HardwareTimer timer;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private double m_TurnAngle;
    private double stop_time;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private boolean  isFirstRun = true;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public Turn(double TurnAngle) {
    	timer = new HardwareTimer();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        m_TurnAngle = TurnAngle;
        stop_time = 0;
        

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.drivetrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	resetCounters();
    }

	private void resetCounters() {
		Robot.drivetrain.getLeftEncoder().reset();
    	Robot.drivetrain.getRightEncoder().reset();
    	Robot.drivetrain.getAnalogGyro1().reset();
	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(isFirstRun){
    		resetCounters();
    		isFirstRun = false;
    	}
    	SmartDashboard.putString("Running Command", "Turn");       	
    	SmartDashboard.putNumber("Left Encoder",Robot.drivetrain.getLeftEncoder().getDistance());
       	SmartDashboard.putNumber("Right Encoder",Robot.drivetrain.getRightEncoder().getDistance());
       	SmartDashboard.putNumber("Gyro",Robot.drivetrain.getAnalogGyro1().getAngle());

       	double error = m_TurnAngle - Robot.drivetrain.getAnalogGyro1().getAngle();
    	if (Math.abs(error)<1){
    		Robot.drivetrain.driveForward(0, 0);
    		if (stop_time < 1){
    			stop_time = timer.getFPGATimestamp();
    		}
    	}
    	else if(Math.abs(error) > 0){
    		if (Math.abs(error) <=25 ){
    			if (error > 0){
    				Robot.drivetrain.driveForward(0, -.3);
    			}
    			else if (error < 0){
    				Robot.drivetrain.driveForward(0, .3);
    			}
    		}
    		else{
    			if (error > 0){
    				Robot.drivetrain.driveForward(0, -.7);
    			}
    			else{
    				Robot.drivetrain.driveForward(0, .7);
    			}
    		}
    		
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double error = m_TurnAngle - Robot.drivetrain.getAnalogGyro1().getAngle();
    	if (Math.abs(error) > 1){
    		stop_time = 0;
    	}
    	
    	double elapsed = timer.getFPGATimestamp() - stop_time;
    	
    	
    	if (elapsed >=.5){
    		resetCounters();
    		return true;
    	}
    	else{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.driveForward(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.driveForward(0, 0);
    }
}
