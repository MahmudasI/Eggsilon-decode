package org.firstinspires.ftc.teamcode.config.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.core.constants.ShooterConfiguration;

public class Shooter extends SubsystemBase implements ShooterConfiguration {
    private final MotorEx shooter;
    boolean isShooterOn;
    Servo lift;
    private double liftPos;

    double STARTING_LIFT_POS = 0.55; // this is the position to witch level lift raises for shooting
    double SHOOTING_LIFT_POS = 0.25; //this is the starting position of lift and/or lift lowest position

    boolean isLiftUp;
    public Shooter(HardwareMap hardwareMap, Telemetry telemetry) {

        shooter = new MotorEx(hardwareMap, "shooter");
        this.isShooterOn = false;
        shooter.setRunMode(Motor.RunMode.RawPower);
        shooter.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        lift = hardwareMap.get(Servo.class, "lift");
        isLiftUp = false;
        lowerLift();
    }

    public void start() {
        shooter.set(1);
    }
    public void stop() {
        shooter.stopMotor();
    }

    public void toggle() {
        if (!isShooterOn) {
            start();
            isShooterOn = true;
        } else {
            stop();
            isShooterOn = false;
        }
    }
    public void setLiftPos(double pos) {
        this.liftPos = pos;
        lift.setPosition(liftPos);
    }

    public void  incrementLift(double change){
        double mappedChange = Math.min(1, Math.max(0, liftPos + change));
        setLiftPos(mappedChange);
        this.liftPos = mappedChange;
    }
    public void raiseLift(){
        setLiftPos(SHOOTING_LIFT_POS);
        isLiftUp = true;
    }
    public  void lowerLift(){
        setLiftPos(STARTING_LIFT_POS);
        isLiftUp = false;
    }
    public void toggleLift(){
        if (!isLiftUp){
            raiseLift();
        } else {
            lowerLift();
        }
    }
}
