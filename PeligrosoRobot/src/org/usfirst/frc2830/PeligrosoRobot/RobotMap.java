// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2830.PeligrosoRobot;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Encoder.IndexingType;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static SpeedController drivetrainRearLeft;
    public static SpeedController drivetrainFrontLeft;
    public static SpeedController drivetrainRearRight;
    public static SpeedController drivetrainFrontRight;
    public static RobotDrive drivetrainRobotDrive41;
    public static AnalogGyro drivetrainAnalogGyro1;
    public static Encoder drivetrainleftEncoder;
    public static Encoder drivetrainrightEncoder;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        drivetrainRearLeft = new Spark(0);
        LiveWindow.addActuator("Drivetrain", "RearLeft", (Spark) drivetrainRearLeft);
        
        drivetrainFrontLeft = new Spark(1);
        LiveWindow.addActuator("Drivetrain", "FrontLeft", (Spark) drivetrainFrontLeft);
        
        drivetrainRearRight = new Spark(2);
        LiveWindow.addActuator("Drivetrain", "RearRight", (Spark) drivetrainRearRight);
        
        drivetrainFrontRight = new Spark(3);
        LiveWindow.addActuator("Drivetrain", "FrontRight", (Spark) drivetrainFrontRight);
        
        drivetrainRobotDrive41 = new RobotDrive(drivetrainRearLeft, drivetrainFrontLeft,
              drivetrainRearRight, drivetrainFrontRight);
        
        drivetrainRobotDrive41.setSafetyEnabled(true);
        drivetrainRobotDrive41.setExpiration(0.1);
        drivetrainRobotDrive41.setSensitivity(0.5);
        drivetrainRobotDrive41.setMaxOutput(1.0);

        drivetrainAnalogGyro1 = new AnalogGyro(1);
        LiveWindow.addSensor("Drivetrain", "AnalogGyro1", drivetrainAnalogGyro1);
        drivetrainAnalogGyro1.setSensitivity(0.007);
        drivetrainleftEncoder = new Encoder(0, 1, false);
        LiveWindow.addSensor("Drivetrain", "leftEncoder", drivetrainleftEncoder);
        drivetrainleftEncoder.setDistancePerPulse(0.0527);
        drivetrainleftEncoder.setPIDSourceType(PIDSourceType.kRate);
        drivetrainleftEncoder.setIndexSource(2, IndexingType.kResetOnRisingEdge);
        drivetrainrightEncoder = new Encoder(3, 4, false);
        LiveWindow.addSensor("Drivetrain", "rightEncoder", drivetrainrightEncoder);
        drivetrainrightEncoder.setDistancePerPulse(0.0527);
        drivetrainrightEncoder.setPIDSourceType(PIDSourceType.kRate);
        drivetrainrightEncoder.setIndexSource(5, IndexingType.kResetOnRisingEdge);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        drivetrainAnalogGyro1.reset();
        drivetrainrightEncoder.reset();
        drivetrainleftEncoder.reset();
  /**
   * 
   *
       
        Thread t = new Thread(() -> {
    		
    		boolean allowCam1 = false;
        UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
        camera1.setResolution(320, 240);
        camera1.setFPS(30);
        UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
        camera2.setResolution(320, 240);
        camera2.setFPS(30);
        
        CvSink cvSink1 = CameraServer.getInstance().getVideo(camera1);
        CvSink cvSink2 = CameraServer.getInstance().getVideo(camera2);
        CvSource outputStream = CameraServer.getInstance().putVideo("Switcher", 320, 240);
        
        Mat image = new Mat();
        
        while(!Thread.interrupted()) {
        	
        	if(Robot.oi.getDriverJoystick().getRawButton(4)) {
        		allowCam1 = !allowCam1;
        	}
        	
            if(allowCam1){
              cvSink2.setEnabled(false);
              cvSink1.setEnabled(true);
              cvSink1.grabFrame(image);
            } else{
              cvSink1.setEnabled(false);
              cvSink2.setEnabled(true);
              cvSink2.grabFrame(image);     
            }
            
            outputStream.putFrame(image);
        }
        
       });
       t.start();
       **/
    }
}
