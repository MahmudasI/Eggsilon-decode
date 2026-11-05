package org.firstinspires.ftc.teamcode.opmodes;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.config.subsystems.Intake;
import org.firstinspires.ftc.teamcode.config.subsystems.Shooter;

@TeleOp(group = "", name = "drivetest")
public class drivetest extends OpMode {

    private DcMotor front_left = null;
    private DcMotor front_right = null;
    private DcMotor back_left = null;
    private DcMotor back_right = null;

    private Shooter shooter;
    boolean prevStateY = false;

    private Intake intake;


    // Speed control
    private double speedMultiplier = 1.0;

    private boolean lastA = false;

    private boolean lastY = false;

    private boolean lastDpadUp = false;
    private boolean lastDpadDown = false;
    private boolean lastDpadLeft = false;
    private boolean lastDpadRight = false;

    private boolean lastB = false;

    @Override
    public void init() {

        // Get motors from Robot Controller configuration
        front_left = hardwareMap.get(DcMotor.class, "leftFront");
        front_right = hardwareMap.get(DcMotor.class, "rightFront");
        back_left = hardwareMap.get(DcMotor.class, "leftRear");
        back_right = hardwareMap.get(DcMotor.class, "rightRear");

        shooter = new Shooter(hardwareMap, telemetry);

        intake = new Intake(hardwareMap, telemetry);

        // Reverse left-front and rear-right motors
        front_left.setDirection(DcMotor.Direction.REVERSE);
        back_left.setDirection(DcMotor.Direction.REVERSE);


        telemetry.addLine("Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {

        // =========================
        // SPEED CONTROL
        // =========================

        // A = toggle between FULL and HALF
        if (gamepad1.a && !lastA) {
            if (speedMultiplier == 1.0) {
                speedMultiplier = 0.5;
            } else {
                speedMultiplier = 1.0;
            }
        }

        lastA = gamepad1.a;

        if(gamepad1.y && !lastY){
            shooter.toggle();
        }

        lastY = gamepad1.y;


        // D-PAD UP = increase speed
        if (gamepad1.dpad_up && !lastDpadUp) {
            speedMultiplier += 0.1;
            speedMultiplier = Math.min(speedMultiplier, 1.0);
        }

        lastDpadUp = gamepad1.dpad_up;


        // D-PAD DOWN = decrease speed
        if (gamepad1.dpad_down && !lastDpadDown) {
            speedMultiplier -= 0.1;
            speedMultiplier = Math.max(speedMultiplier, 0.2);
        }

        lastDpadDown = gamepad1.dpad_down;


        if (gamepad1.b && !lastB) {
            intake.toggle();
        }
        if(gamepad1.xWasPressed()){
            intake.revtoggle();
        }

        lastB = gamepad1.b;


        // =========================
        // CONTROLLER INPUT
        // =========================

        // Negative because pushing joystick forward gives a negative Y value
        double drive = gamepad1.left_stick_y;

        // Left/right
        double strafe = -gamepad1.left_stick_x;

        // Rotation
        double twist = gamepad1.right_stick_x;


        // =========================
        // MECANUM CALCULATIONS
        // =========================

        double frontLeft = drive + strafe + twist;
        double frontRight = drive - strafe - twist;
        double backLeft = drive - strafe + twist;
        double backRight = drive + strafe - twist;


        // =========================
        // NORMALIZE
        // =========================

        double max = Math.max(
                1.0,
                Math.max(
                        Math.abs(frontLeft),
                        Math.max(
                                Math.abs(frontRight),
                                Math.max(
                                        Math.abs(backLeft),
                                        Math.abs(backRight)
                                )
                        )
                )
        );

        frontLeft /= max;
        frontRight /= max;
        backLeft /= max;
        backRight /= max;


        // =========================
        // APPLY SPEED LIMIT
        // =========================

        frontLeft *= speedMultiplier;
        frontRight *= speedMultiplier;
        backLeft *= speedMultiplier;
        backRight *= speedMultiplier;


        // =========================
        // SET MOTOR POWER
        // =========================

        front_left.setPower(frontLeft);
        front_right.setPower(frontRight);
        back_left.setPower(backLeft);
        back_right.setPower(backRight);

        if(gamepad1.rightBumperWasPressed()){
            shooter.toggleLift();
            shooter.toggle();
            intake.toggleout();
        }

        // GAMEPAD 1: SHOOTER SPEED CONTROL

        // D-pad RIGHT: increase shooter speed by 5%
        if (gamepad1.dpad_right && !lastDpadRight) {
            shooter.increaseShooterSpeed();
        }
        lastDpadRight = gamepad1.dpad_right;

        // D-pad LEFT: decrease shooter speed by 5%
        if (gamepad1.dpad_left && !lastDpadLeft) {
            shooter.decreaseShooterSpeed();
        }
        lastDpadLeft = gamepad1.dpad_left;


        // =========================
        // TELEMETRY
        // =========================

        telemetry.addData("Speed", "%.0f%%", speedMultiplier * 100);

        telemetry.addData("Front Left", frontLeft);
        telemetry.addData("Front Right", frontRight);
        telemetry.addData("Back Left", backLeft);
        telemetry.addData("Back Right", backRight);
        telemetry.addData(
                "Shooter Speed",
                "%.0f%%",
                shooter.getShooterSpeed() * 100
        );

        telemetry.addData(
                "Shooter Status",
                shooter.isShooterOn() ? "ON" : "OFF"
        );

        telemetry.update();
    }
}
