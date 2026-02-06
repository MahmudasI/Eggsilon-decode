package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.config.subsystems.Chassis;
import org.firstinspires.ftc.teamcode.config.subsystems.Intake;
import org.firstinspires.ftc.teamcode.config.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.config.subsystems.Sorting;

@TeleOp(group = "", name = "EggsilonTeleOp")
public class EggsilonTeleOp extends LinearOpMode {


    boolean prevStateA, prevStateB, prevStateX = false;
    @Override
    public void runOpMode() {

        Chassis chassis = new Chassis(hardwareMap, telemetry);
        Shooter shooter = new Shooter(hardwareMap, telemetry);
        Intake intake = new Intake(hardwareMap, telemetry);
        Sorting sorting = new Sorting(hardwareMap, telemetry);

        Servo lift;
        lift = hardwareMap.get(Servo.class, "lift");
        Servo sort;
        sort = hardwareMap.get(Servo.class, "sorting");

        GamepadEx firstDriver = new GamepadEx(gamepad1);

        waitForStart();




        while (!isStopRequested()) {
            /// Drivetrain
            double forward = gamepad1.left_stick_y;
            double strafe = -gamepad1.left_stick_x;
            chassis.drive(
                    firstDriver.getLeftX(),
                    firstDriver.getLeftY(),
                    firstDriver.getRightX()
            );


            if (gamepad1.a && !prevStateA) {
                shooter.toggle();
            }

            if (gamepad1.b && !prevStateB) {
                intake.toggle();

            }

            if(gamepad1.xWasPressed()){
                intake.revtoggle();
            }

            if(gamepad1.rightBumperWasPressed()){
                shooter.toggleLift();
            }
            prevStateA = gamepad1.a;
            prevStateB = gamepad1.b;
            prevStateX = gamepad1.x;
            telemetry.update();
        }
    }

}
