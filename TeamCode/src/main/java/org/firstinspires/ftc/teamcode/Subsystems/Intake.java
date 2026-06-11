package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    public enum IntakeState{
        INTAKE,
        OUTTAKE,
        OFF,
    }
    private IntakeState state = IntakeState.OFF;
    private DcMotorEx intakeMotor;
    public Intake(HardwareMap hwMap){
        intakeMotor = hwMap.get(DcMotorEx.class, "intakeMotor");
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intakeMotor.setPower(0);
    }
    public void intakeTask(){
        switch (state){
            case INTAKE:
                intakeMotor.setPower(1);
                break;
            case OUTTAKE:
                intakeMotor.setPower(-1);
                break;
            case OFF:
                intakeMotor.setPower(0);
                break;
        }
    }
    public IntakeState getState(){
        return state;
    }
    public void setState(IntakeState state){
        this.state = state;
    }
}
