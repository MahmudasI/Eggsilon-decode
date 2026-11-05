package org.firstinspires.ftc.teamcode.config.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.core.constants.IntakeConfiguration;

public class Intake extends SubsystemBase implements IntakeConfiguration {
    private MotorEx intake1;
    private MotorEx intake2;
    boolean isIntakeOn;
    boolean isOutakeOn;
    boolean isRevIntakeOn; //reverse intake

    public Intake(HardwareMap hardwareMap, Telemetry telemetry) {

        intake1 = new MotorEx(hardwareMap, "intake1");
        intake2 = new MotorEx(hardwareMap, "intake2");
        this.isIntakeOn = false;
        this.isOutakeOn = false;
        this.isRevIntakeOn = false;
        intake1.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        intake2.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        intake1.setInverted(true);
        intake2.setInverted(false);
    }

    public void startintake() {
        intake1.set(1);
    }

    public void stopintake() {
        intake1.stopMotor();
    }
    public void startoutake(){
        intake1.set(1);
        intake2.set(1);
    }
    public void stopoutake(){
        intake1.stopMotor();
        intake2.stopMotor();
    }

    public void toggle() {
        if (!isIntakeOn) {
            startintake();
            isIntakeOn = true;
        } else {
            stopintake();
            isIntakeOn = false;
        }
    }
    public void toggleout(){
        if(!isOutakeOn){
            startoutake();
            isOutakeOn = true;
        } else {
            stopoutake();
            isOutakeOn = false;
        }
    }
    public void revtoggle(){
        if (!isRevIntakeOn){
            intake1.setInverted(false);
            isRevIntakeOn = true;
        } else {
            intake1.setInverted(true);
            isRevIntakeOn = false;
        }
    }
}
