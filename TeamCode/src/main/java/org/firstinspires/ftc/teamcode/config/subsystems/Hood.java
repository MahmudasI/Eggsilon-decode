package org.firstinspires.ftc.teamcode.config.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Hood {

    Servo hood;
    private double hoodPos;

    double STARTING_HOOD_POS = 0.55; // this is the position to witch level lift raises for shooting
    public Hood(HardwareMap hardwareMap, Telemetry telemetry) {

        hood = hardwareMap.get(Servo.class, "hood");
    }
    public void setHoodPos(double pos) {
        this.hoodPos = pos;
        hood.setPosition(hoodPos);
    }
    public void  incrementHood(double change){
        double mappedChange = Math.min(1, Math.max(0, hoodPos + change));
        setHoodPos(mappedChange);
        this.hoodPos = mappedChange;
    }
}
