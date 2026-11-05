package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Simple Auto", group = "Auto")
public class SimpleAuto extends LinearOpMode {

    private DcMotor front_left;
    private DcMotor front_right;
    private DcMotor back_left;
    private DcMotor back_right;

    @Override
    public void runOpMode() {

        // =========================
        // GET MOTORS
        // =========================

        front_left = hardwareMap.get(DcMotor.class, "leftFront");
        front_right = hardwareMap.get(DcMotor.class, "rightFront");
        back_left = hardwareMap.get(DcMotor.class, "leftRear");
        back_right = hardwareMap.get(DcMotor.class, "rightRear");

        // Same directions as your TeleOp
        front_left.setDirection(DcMotor.Direction.REVERSE);
        back_left.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addLine("Initialized");
        telemetry.update();

        // Wait for driver to press PLAY
        waitForStart();

        if (opModeIsActive()) {


            drive(0.5, 0, 0);

            sleep(1000); // Drive forward for 2 seconds

            stopMotors();


            // =========================
            // 2. STRAFE LEFT
            // =========================

        }
    }


    // =========================
    // DRIVE FUNCTION
    // =========================

    private void drive(double drive, double strafe, double twist) {

        double frontLeft = drive + strafe + twist;
        double frontRight = drive - strafe - twist;
        double backLeft = drive - strafe + twist;
        double backRight = drive + strafe - twist;

        // Normalize
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

        front_left.setPower(frontLeft);
        front_right.setPower(frontRight);
        back_left.setPower(backLeft);
        back_right.setPower(backRight);
    }


    // =========================
    // STOP MOTORS
    // =========================

    private void stopMotors() {

        front_left.setPower(0);
        front_right.setPower(0);
        back_left.setPower(0);
        back_right.setPower(0);
    }
}
