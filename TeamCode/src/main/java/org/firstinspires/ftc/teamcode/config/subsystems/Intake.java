package org.firstinspires.ftc.teamcode.config.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.core.constants.IntakeConfiguration;

public class Intake extends SubsystemBase implements IntakeConfiguration {
    private MotorEx intake;
    boolean isIntakeOn;
    boolean isRevIntakeOn; //reverse intake

    public Intake(HardwareMap hardwareMap, Telemetry telemetry) {

        intake = new MotorEx(hardwareMap, "intake");
        this.isIntakeOn = false;
        this.isRevIntakeOn = false;
        intake.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        intake.setInverted(true);
    }

    public void start() {
        intake.set(1);
    }

    public void stop() {
        intake.stopMotor();
    }

    public void toggle() {
        if (!isIntakeOn) {
            start();
            isIntakeOn = true;
        } else {
            stop();
            isIntakeOn = false;
        }
    }
    public void revtoggle(){
        if (!isRevIntakeOn){
            intake.setInverted(false);
            isRevIntakeOn = true;
        } else {
            intake.setInverted(true);
            isRevIntakeOn = false;
        }
    }
}
