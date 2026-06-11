package org.firstinspires.ftc.teamcode.Subsystems;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

public class Drivetrain {
    private DcMotorEx lb;
    private DcMotorEx rb;
    private DcMotorEx lf;
    private DcMotorEx rf;
    private double speed;
    public Drivetrain(HardwareMap hwMap){
        //defining
        lb = hwMap.get(DcMotorEx.class, "lb");
        rb = hwMap.get(DcMotorEx.class, "rb");
        lf = hwMap.get(DcMotorEx.class, "lf");
        rf = hwMap.get(DcMotorEx.class, "rf");

        //zero power behavior
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rb.setDirection(DcMotorSimple.Direction.FORWARD);
        rf.setDirection(DcMotorSimple.Direction.FORWARD);
        lb.setDirection(DcMotorSimple.Direction.REVERSE);
        lf.setDirection(DcMotorSimple.Direction.REVERSE);

        lb.setPower(0);
        rb.setPower(0);
        lf.setPower(0);
        rf.setPower(0);
    }
    public void move(Gamepad gamepad1){
        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double lbPower = Range.clip((y - x + rx) / denominator, -speed, speed);
        double lfPower = Range.clip((y + x + rx) / denominator, -speed, speed);
        double rbPower = Range.clip((y + x - rx) / denominator, -speed, speed);
        double rfPower = Range.clip((y - x - rx) / denominator, -speed, speed);

        lb.setPower(lbPower);
        rb.setPower(lfPower);
        lf.setPower(rbPower);
        rf.setPower(rfPower);
    }
    public double getSpeed(){
        return speed;
    }
    public void setSpeed(double speed){
        this.speed = speed;
    }
}
