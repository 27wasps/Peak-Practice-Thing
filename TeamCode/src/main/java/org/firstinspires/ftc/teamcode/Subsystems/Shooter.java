package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {
    public enum ShooterState{
        SHOOT,
        DROP,
        OFF,
    }
    private Shooter.ShooterState state = Shooter.ShooterState.OFF;
    private DcMotorEx shooterA;
    private DcMotorEx shooterB;
    public Shooter(HardwareMap hwMap){
        shooterA = hwMap.get(DcMotorEx.class, "shooterA");
        shooterA.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterA.setPower(0);
        shooterA.setDirection(DcMotorSimple.Direction.FORWARD);
        shooterB = hwMap.get(DcMotorEx.class, "shooterA");
        shooterB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterB.setPower(0);
        shooterB.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void shootTask(double targetVelocity){
        switch (state){
            case SHOOT:
                setVelocity(targetVelocity);
                break;
            case DROP:
                setVelocity(targetVelocity*0.2);
                break;
            case OFF:
                setVelocity(0);
                break;
        }
    }
    public void setVelocity(double targetVelocity){
        shooterA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterA.setVelocity(targetVelocity);
        shooterB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterB.setVelocity(targetVelocity);
    }
    public Shooter.ShooterState getState(){
        return state;
    }
    public void setState(Shooter.ShooterState state){
        this.state = state;
    }
}
