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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc2830.PeligrosoRobot.Robot;

/**
 *
 */
public class DriveDistance extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private double m_distance;
    private double m_speed;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private boolean isFirstRun = true;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveDistance(double distance, double speed) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        m_distance = distance;
        m_speed = speed;

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.drivetrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    public DriveDistance(double distance) {
    	this(distance, 0.7);
        }
   
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetCounters();
    }

	

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(isFirstRun){
    		Robot.drivetrain.resetCounters();
    		isFirstRun = false;
    	}
    	SmartDashboard.putString("Running Command", "DriveDistance");
    	Robot.drivetrain.writeToSmartDashboard();
    	Robot.oi.writeToSmartDashboard();
    	
    	/*if(Robot.drivetrain.getAnalogGyro1().getAngle()>1.00){
    		Robot.drivetrain.driveForward(m_speed,.3);
    	}
    	else if(Robot.drivetrain.getAnalogGyro1().getAngle()<-1.00){
    		Robot.drivetrain.driveForward(m_speed,-.3);
    	}
    	else{
    		Robot.drivetrain.driveForward(m_speed,0);
    	}*/
       	double v;
       	double x = Robot.drivetrain.getRightEncoder().getDistance();
       	double vMin = .6;
       	double xRamp = Math.min(18,m_distance/2);
       	double xBrake = Math.min(18,m_distance/2);
       	if (x>xBrake)
       		v=m_speed+((vMin-m_speed)/(m_distance-xBrake))*x;
       	else if (x<xRamp)
       		v=vMin+((m_speed-vMin)/xRamp)*x;
       	else
      		v=m_speed;
       	
       	if(Robot.drivetrain.getAnalogGyro1().getAngle()>1.00){
    		Robot.drivetrain.driveForward(v,.3);
    	}
    	else if(Robot.drivetrain.getAnalogGyro1().getAngle()<-1.00){
    		Robot.drivetrain.driveForward(v,-.3);
    	}
    	else{
    		Robot.drivetrain.driveForward(v,0);
    	}
       	
       	
    }
  

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(m_distance <= Robot.drivetrain.getLeftEncoder().getDistance() || m_distance <= Robot.drivetrain.getRightEncoder().getDistance()){
    		Robot.drivetrain.resetCounters();
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
