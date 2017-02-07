// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2830.PeligrosoRobot.subsystems;

import org.usfirst.frc2830.PeligrosoRobot.RobotMap;
import org.usfirst.frc2830.PeligrosoRobot.commands.*;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Drivetrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController rearLeft = RobotMap.drivetrainRearLeft;
    private final SpeedController frontLeft = RobotMap.drivetrainFrontLeft;
    private final SpeedController rearRight = RobotMap.drivetrainRearRight;
    private final SpeedController frontRight = RobotMap.drivetrainFrontRight;
    private final RobotDrive robotDrive41 = RobotMap.drivetrainRobotDrive41;
    private final AnalogGyro analogGyro1 = RobotMap.drivetrainAnalogGyro1;
    private final Encoder leftEncoder = RobotMap.drivetrainleftEncoder;
    private final Encoder rightEncoder = RobotMap.drivetrainrightEncoder;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	static double controllerCorrection = .35;


    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new TankDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        analogGyro1.reset();
        leftEncoder.reset();
        rightEncoder.reset();
    }
    
    public void driveTank(Joystick joystick){
    /*
     * 
		double rightCalc = joystick.getRawAxis(3)+ Math.copySign((controllerCorrection - controllerCorrection* Math.abs(joystick.getRawAxis(3))),joystick.getRawAxis(3));
		double leftCalc = joystick.getRawAxis(1)+  Math.copySign(controllerCorrection - controllerCorrection* Math.abs(joystick.getRawAxis(1)),joystick.getRawAxis(1));

    	SmartDashboard.putNumber("leftCalc",leftCalc);
		SmartDashboard.putNumber("rightCalc",rightCalc * -1);
				if (Math.abs(rightCalc) > .38 || Math.abs(leftCalc) > .38){
		*/
		SmartDashboard.putNumber("left",joystick.getRawAxis(1) * -1);
		SmartDashboard.putNumber("right",joystick.getRawAxis(3) * -1);
		SmartDashboard.putNumber("Left Encoder",leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Encoder",rightEncoder.getDistance());		
		SmartDashboard.putNumber("Gyro",analogGyro1.getAngle());

			robotDrive41.tankDrive(joystick.getRawAxis(1) * -1, joystick.getRawAxis(3) *-1,true);

    }
    
    public void driveForward(double speed){
    	
    	robotDrive41.arcadeDrive(speed, 0);
    }
}

