package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * This is NOT an opmode.
 *
 * This class is the hardware map of the robot.
 */
public class Testbot
{
    /* Public OpMode members. */
    public DcMotor frontLeft = null;
    public DcMotor frontRight = null;
    public DcMotor backLeft = null;
    public DcMotor backRight = null;
    public CRServo servo1 = null;
    public CRServo servo2 = null;
    public CRServo servo3 = null;
    public CRServo servo4 = null;
    public DcMotor extra1 = null;
    public DcMotor extra2 = null;
    public ColorSensor colorSense = null;
    
    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    //private ElapsedTime period  = new ElapsedTime();

    /* Constructor */  
    public Testbot(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        frontLeft   = ahwMap.dcMotor.get("frontLeft");
        frontRight  = ahwMap.dcMotor.get("frontRight");
        backLeft    = ahwMap.dcMotor.get("backLeft");
        backRight   = ahwMap.dcMotor.get("backRight");
        colorSense  = ahwMap.colorSensor.get("sensor_color_distance");
        servo1   = ahwMap.crservo.get("servo1");
        servo2   = ahwMap.crservo.get("servo2");
        servo3   = ahwMap.crservo.get("servo3");
        servo4   = ahwMap.crservo.get("servo4");
        extra1   = ahwMap.dcMotor.get("extra1");
        extra2   = ahwMap.dcMotor.get("extra2");


        /*frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);*/
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        extra1.setPower(0);
        extra2.setPower(0);

        // Set all motors to run using encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extra1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extra2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     * @throws InterruptedException
     */
    /*public void waitForTick(long periodMs) throws InterruptedException {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }*/
}
