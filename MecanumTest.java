package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="MecanumTest", group="Mecanum")
public class MecanumTest extends OpMode{

    /* Declare OpMode members. */
    private Testbot robot = new Testbot(); // use the class created to define a Mecanobot's hardware


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        telemetry.addData("Robot", "ready");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        float Ch1,Ch3,Ch4,FrontLeft,BackLeft,FrontRight,BackRight;

        //Mecanum drive
        Ch1 = gamepad1.right_stick_x;
        Ch3 = -gamepad1.left_stick_y;
        Ch4 = gamepad1.left_stick_x;

        FrontLeft = Ch3 + Ch1 + Ch4;
        BackLeft = Ch3 - Ch1 - Ch4;
        FrontRight = Ch3 + Ch1 - Ch4;
        BackRight = Ch3 - Ch1 + Ch4;

        /*FrontLeft = Ch3 + Ch1 + Ch4;
        BackLeft = Ch3 + Ch1 - Ch4;     //wheelie turns
        FrontRight = Ch3 - Ch1 - Ch4;
        BackRight = Ch3 - Ch1 + Ch4;*/

        setAllS(FrontLeft, BackLeft, FrontRight, BackRight);

        telemetry.addData("frontRight", FrontRight);
        telemetry.addData("frontLeft", FrontLeft);
        telemetry.addData("backRight", BackRight);
        telemetry.addData("backLeft", BackLeft);

        updateTelemetry(telemetry);
        
        /*if(gamepad2.y) {
            robot.frontLeft.setPower(1);
            telemetry.addData("frontLeft", "driving");
            updateTelemetry(telemetry);
        }
        else if(gamepad2.x) {
            robot.frontRight.setPower(1);
            telemetry.addData("frontRight", "driving");
            updateTelemetry(telemetry);
        }
        else if(gamepad2.a) {
            robot.backLeft.setPower(1);
            telemetry.addData("backLeft", "driving");
            updateTelemetry(telemetry);
        }
        else if(gamepad2.b) {
            robot.backRight.setPower(1);
            telemetry.addData("backRight", "driving");
            updateTelemetry(telemetry);
        }*/
        
        if(gamepad1.y) {
            timeDrive(0, 1, 1);
       }
        else if(gamepad1.x) {
            timeDrive(1, 0, 1);
        }
        else if(gamepad1.a) {
            timeDrive(0, -1, 1);
        }
        else if(gamepad1.b) {
            timeDrive(-1, 0, 1);
        }
        
        if(gamepad2.a) {
            setServos(false, 1);
        }
        else if(gamepad2.b) {
            setServos(false, -1);
        }
        else {
            setServos(false, 0);
        }
        
        if(gamepad2.x) {
            setServos(true, 1);
        }
        else if(gamepad2.y) {
            setServos(true, -1);
        }
        else {
            setServos(true, 0);
        }
        
        if(gamepad1.dpad_up) {
            robot.extra1.setPower(1);
            robot.extra2.setPower(1);
        }
        else if(gamepad1.dpad_down) {
            robot.extra1.setPower(-1);
            robot.extra2.setPower(-1);
        }
        else {
            robot.extra1.setPower(0);
            robot.extra2.setPower(0);
        }

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
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
    
    private void setServos(boolean set, float speed) {
        if(set) {
            robot.servo1.setPower(speed);
            robot.servo2.setPower(-speed);
        }
        else {
            robot.servo3.setPower(speed);
            robot.servo4.setPower(-speed);
        }
    }
    
    private void wait(int time) {
        try {
            Thread.sleep(time);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}





