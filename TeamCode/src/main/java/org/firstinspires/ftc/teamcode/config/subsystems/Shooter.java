
package org.firstinspires.ftc.teamcode.config.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.core.constants.ShooterConfiguration;

public class Shooter extends SubsystemBase implements ShooterConfiguration {

    private final MotorEx bshooter;
    private final MotorEx tshooter;

    private boolean isShooterOn = false;
    private boolean isLiftUp = false;

    private Servo sright;
    private Servo sleft;

    private double srightPos;
    private double sleftPos;

    private double shooterSpeed = 1.0;
    private final double MIN_SHOOTER_SPEED = 0.20;
    private final double MAX_SHOOTER_SPEED = 1.0;
    private final double SHOOTER_SPEED_STEP = 0.05;

    private final double STARTING_LIFT_POS = 0.4;
    private final double SHOOTING_LIFT_POS = 0.6;

    public Shooter(HardwareMap hardwareMap, Telemetry telemetry) {

        bshooter = new MotorEx(hardwareMap, "bshooter");
        tshooter = new MotorEx(hardwareMap, "tshooter");

        bshooter.setRunMode(Motor.RunMode.RawPower);
        bshooter.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        tshooter.setRunMode(Motor.RunMode.RawPower);
        tshooter.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        tshooter.setInverted(true);

        sright = hardwareMap.get(Servo.class, "sright");
        sleft = hardwareMap.get(Servo.class, "sleft");

        lowerLift();
    }

    public void bstart() {
        bshooter.set(shooterSpeed);
    }

    public void tstart() {
        tshooter.set(shooterSpeed);
    }

    public void bstop() {
        bshooter.stopMotor();
    }

    public void tstopt() {
        tshooter.stopMotor();
    }

    public void toggle() {
        if (!isShooterOn) {
            bstart();
            tstart();
            isShooterOn = true;
        } else {
            bstop();
            tstopt();
            isShooterOn = false;
        }
    }

    public void increaseShooterSpeed() {
        setShooterSpeed(shooterSpeed + SHOOTER_SPEED_STEP);
    }

    public void decreaseShooterSpeed() {
        setShooterSpeed(shooterSpeed - SHOOTER_SPEED_STEP);
    }

    public void setShooterSpeed(double speed) {
        shooterSpeed = Math.max(
                MIN_SHOOTER_SPEED,
                Math.min(MAX_SHOOTER_SPEED, speed)
        );

        // Apply the new speed immediately if the shooter is running.
        if (isShooterOn) {
            bshooter.set(shooterSpeed);
            tshooter.set(shooterSpeed);
        }
    }

    public double getShooterSpeed() {
        return shooterSpeed;
    }

    public boolean isShooterOn() {
        return isShooterOn;
    }

    public void setLiftPos(double pos) {
        pos = Math.max(0.0, Math.min(1.0, pos));

        sleftPos = pos;
        srightPos = pos;

        sright.setPosition(srightPos);
        sleft.setPosition(sleftPos);
    }

    public void raiseLift() {
        setLiftPos(SHOOTING_LIFT_POS);
        isLiftUp = true;
    }

    public void lowerLift() {
        setLiftPos(STARTING_LIFT_POS);
        isLiftUp = false;
    }

    public void toggleLift() {
        if (!isLiftUp) {
            raiseLift();
        } else {
            lowerLift();
        }
    }
}
