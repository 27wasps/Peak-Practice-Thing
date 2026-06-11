package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Transfer {
    public enum Color{
        GREEN,
        PURPLE,
        NULL,
    }
    DcMotorEx spindexer;
    RevColorSensorV3 color;
    String[] indexBallOrder = {"null", "null", "null"};
    String[] motif = {"green", "green", "purple"};
    int position;
    int currentInPosition = 0;
    public Transfer(HardwareMap hwMap) {
        spindexer = hwMap.get(DcMotorEx.class, "spindexer");
        spindexer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        spindexer.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spindexer.setDirection(DcMotorSimple.Direction.FORWARD);
        spindexer.setPower(0);
    }

    public Transfer.Color getColor() {
        int red = color.red();
        int green = color.green();
        int blue = color.blue();

        if (red > 80 && blue > 80 && green < 60) {
            return Color.PURPLE;
        } else if (red < 100 && blue < 100 && green > 100) {
            return Color.GREEN;
        } else {
            return Color.NULL;
        }
    }

    public
}
