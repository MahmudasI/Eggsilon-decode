package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.config.subsystems.Chassis;
import org.firstinspires.ftc.teamcode.config.subsystems.Hood;
import org.firstinspires.ftc.teamcode.config.subsystems.Intake;
import org.firstinspires.ftc.teamcode.config.subsystems.Shooter;
@TeleOp(group = "", name = "drivetest")
public class drivetest extends LinearOpMode {

    @Override
    public void runOpMode() {

        Chassis chassis = new Chassis(hardwareMap, telemetry);
        Shooter shooter = new Shooter(hardwareMap, telemetry);
        Hood hood = new Hood(hardwareMap, telemetry);
        //Intake intake = new Intake(hardwareMap, telemetry);

        Servo lift;
        lift = hardwareMap.get(Servo.class, "lift");

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


            //if (gamepad1.aWasPressed()) {
            //shooter.toggle();
            //}

            //if (gamepad1.bWasPressed()) {
            //intake.toggle();
            //}

            if(gamepad1.dpadDownWasPressed()){
                hood.incrementHood(-0.05);
            }
            if (gamepad1.dpadUpWasPressed()){
                hood.incrementHood(0.05);
            }
            if(gamepad1.rightBumperWasPressed()){
                shooter.toggleLift();
            }
            telemetry.update();
        }
    }

}
