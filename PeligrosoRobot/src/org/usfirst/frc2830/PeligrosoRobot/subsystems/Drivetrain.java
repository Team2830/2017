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

import org.usfirst.frc2830.PeligrosoRobot.Robot;
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
	
	/**
	 * Deadband is the range of numbers smaller than that we are going to ignore. For example, if the deadband = .02, 
	 * then if the joystick inputs of < .02 and > -.02 are treated as 0
	 */
	static double joystickDeadband = .02;




	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new splitArcadeDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		getAnalogGyro1().reset();
		getLeftEncoder().reset();
		getRightEncoder().reset();
	}

    public void driveTank(Joystick joystick){
        
		double rightCalc = -1* joystick.getRawAxis(3)+ Math.copySign((controllerCorrection - controllerCorrection* Math.abs(joystick.getRawAxis(3))),joystick.getRawAxis(3));
		double leftCalc = -1 * joystick.getRawAxis(1)+  Math.copySign(controllerCorrection - controllerCorrection* Math.abs(joystick.getRawAxis(1)),joystick.getRawAxis(1));

    	SmartDashboard.putNumber("leftCalc",leftCalc);
		SmartDashboard.putNumber("rightCalc",rightCalc * -1);
				if (Math.abs(rightCalc) > (controllerCorrection+.03) || Math.abs(leftCalc) > (controllerCorrection +.03)){
		

		//	robotDrive41.tankDrive(joystick.getRawAxis(1) * -1, joystick.getRawAxis(3) *-1,true);
		robotDrive41.tankDrive(leftCalc, rightCalc);
				} else {
					robotDrive41.tankDrive(0, 0);
				}

				
    }

	public void writeToSmartDashboard() {

		SmartDashboard.putNumber("Left Encoder",getLeftEncoder().getDistance());
		SmartDashboard.putNumber("Right Encoder",getRightEncoder().getDistance());		
		SmartDashboard.putNumber("Gyro",getAnalogGyro1().getAngle());
	}

	/**
	 *  This method is used to drive the robot using the arcadeDrive. Parameters are passed to the arcadeDrive method of RobotDrive
	 * @param speed
	 * @param rotation
	 */
	public void driveForward(double speed, double rotation) {

		robotDrive41.arcadeDrive(speed, rotation);
	}

	/**
	 * Implementing a split stick arcade to try to improve handling
	 * 
	 * Leveraging Team 254 Cheesy Drive helper
	 * https://github.com/Team254/FRC-2016-Public
	 * 
	 * @return
	 */
	public void driveSplitArcade(Joystick driverStick) {

		

		// robotDrive41.arcadeDrive(throttle, steering);
		// Make sure the values are outside if the deadband
		double throttle = deadbanded((-1*driverStick.getRawAxis(2)) + driverStick.getRawAxis(3), joystickDeadband);
		double steering = deadbanded(driverStick.getRawAxis(0), joystickDeadband);
		double overPower;
		double angularPower;
		// 254 uses overPower as part of their quickStopAcelerator calculations,
		// which we are not using at this point. leaving at 0
		overPower = 0.0;
		angularPower = Math.abs(throttle) * steering;
		
		double rightPwm = throttle - angularPower;
		double leftPwm = throttle + angularPower;
		if (leftPwm > 1.0) {
			rightPwm -= overPower * (leftPwm - 1.0);
			leftPwm = 1.0;
		} else if (rightPwm > 1.0) {
			leftPwm -= overPower * (rightPwm - 1.0);
			rightPwm = 1.0;
		} else if (leftPwm < -1.0) {
			rightPwm += overPower * (-1.0 - leftPwm);
			leftPwm = -1.0;
		} else if (rightPwm < -1.0) {
			leftPwm += overPower * (-1.0 - rightPwm);
			rightPwm = -1.0;
		}
		robotDrive41.tankDrive(leftPwm, rightPwm);
	}
	public void driveArcade(Joystick driverStick) {
		double throttle = deadbanded((-1*driverStick.getRawAxis(2)) + driverStick.getRawAxis(3), joystickDeadband);
		double steering = deadbanded(-1*driverStick.getRawAxis(0), joystickDeadband);
		robotDrive41.arcadeDrive(throttle, steering, true);
	}

	public Encoder getLeftEncoder() {
		return leftEncoder;
	}

	public Encoder getRightEncoder() {
		return rightEncoder;
	}

	public AnalogGyro getAnalogGyro1() {
		return analogGyro1;
	}

	public void resetCounters() {
		getLeftEncoder().reset();
    	getRightEncoder().reset();
    	getAnalogGyro1().reset();
	}
	public double deadbanded(double val, double deadband) {
		if (Math.abs(val) > Math.abs(deadband)) {
			return val;
		} else {
			return 0.0;
		}
	}
}
