package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import android.graphics.Color;
import com.qualcomm.robotcore.hardware.ColorSensor;
//import android.speech.tts.TextToSpeech;

@Autonomous(name = "TestAutonomous", group = "Sensor")

public class ColorAuto extends LinearOpMode {
    private Testbot robot = new Testbot();
    
    ColorSensor colorSense;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DistanceSensor distanceSense;
    
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        //TextToSpeech.speak("initiating robot uprising", 0, null, "init");
        telemetry.addData("Robot", "ready");
        
        colorSense = hardwareMap.get(ColorSensor.class, "sensor_color_distance");
        
        float hsv[] = {0F, 0F, 0F};
        final float values[] = hsv;
        int scale = 1;
        boolean colorIsRed = false;
        
        waitForStart();
        
        Color.RGBToHSV((int) (colorSense.red() * scale),
                (int) (colorSense.green() * scale),
                (int) (colorSense.blue() * scale),
                hsv);
        
        if(hsv[0] < 100 || hsv[0] > 300) colorIsRed = true;
        else colorIsRed = false;
        
        float speed = (float) 0.6;
        if(colorIsRed) {
            setAll(speed, speed, speed, speed);
            wait(1000);
            setAll(0, 0, 0, 0);
        }
        else {
            setAll(-speed, -speed, -speed, -speed);
            wait(1000);
            setAll(0, 0, 0, 0);
        }
        
        wait(1000);
        
        /*while (opModeIsActive()) {
            Color.RGBToHSV((int) (colorSense.red() * scale),
                            (int) (colorSense.green() * scale),
                            (int) (colorSense.blue() * scale),
                            hsv);

            if(hsv[0] < 100 || hsv[0] > 300) colorIsRed = true;
            else colorIsRed = false;

            telemetry.addData("Alpha", colorSense.alpha());
            telemetry.addData("Red  ", colorSense.red());
            telemetry.addData("Green", colorSense.green());
            telemetry.addData("Blue ", colorSense.blue());
            telemetry.addData("Hue", hsv[0]);
            telemetry.addData("colorIsRed", colorIsRed);
            telemetry.update();
        }*/
            

    }
    
    
    private double si(double joyVal, double divBy) {
        /**
         * This function scales the input.
         * It makes it so that when you push the joystick forward, the robot doesn't respond in a linear way.
         * When you push it a little bit, you have more precision control over speed.
         * Pushing all the way overrides the math and jumps to 1.
         */
        if(joyVal>=1) return 1;
        else if(joyVal<=-1) return -1;
        else return -Math.log(-joyVal + 1) / divBy;
    }

    private void timeDrive(float x, float y,  int time) {
        float fl = x + y;
        float bl = x - y;
        float fr = x - y;
        float br = x + y;
        
        setAll(fl, bl, fr, br);
        wait(1000);
        setAll(0, 0, 0, 0);
    }
    
    private void setAll(float fl, float fr, float bl, float br) {
        robot.frontLeft.setPower(fl);
        robot.frontRight.setPower(fr);
        robot.backLeft.setPower(bl);
        robot.backRight.setPower(br);
    }
    
    private void setAllS(float fl, float fr, float bl, float br) {
        robot.frontLeft.setPower(Range.clip(si(fl, 1.5), -1, 1));
        robot.frontRight.setPower(Range.clip(si(fr, 1.5), -1, 1));
        robot.backLeft.setPower(Range.clip(si(bl, 1.5), -1, 1));
        robot.backRight.setPower(Range.clip(si(br, 1.5), -1, 1));
    }
    
    private void wait(int time) {
        try {
            Thread.sleep(time);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    private void setBrakes() {
        robot.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

}