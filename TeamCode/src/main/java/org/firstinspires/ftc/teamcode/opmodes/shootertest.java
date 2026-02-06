package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.config.subsystems.Chassis;
import org.firstinspires.ftc.teamcode.config.subsystems.Shooter;
@TeleOp(group = "", name = "shootertest")
public class shootertest extends LinearOpMode {

    @Override
    public void runOpMode() {

        //Chassis chassis = new Chassis(hardwareMap, telemetry);
        Shooter shooter = new Shooter(hardwareMap, telemetry);
        //Intake intake = new Intake(hardwareMap, telemetry);

        Servo lift;
        lift = hardwareMap.get(Servo.class, "lift");

        GamepadEx firstDriver = new GamepadEx(gamepad1);

        waitForStart();



        while (!isStopRequested()) {
            /// Drivetrain
            //double forward = gamepad1.left_stick_y;
            //double strafe = -gamepad1.left_stick_x;
            //chassis.drive(
                   // firstDriver.getLeftX(),
                    //firstDriver.getLeftY(),
                    //firstDriver.getRightX()
           // );


            if (gamepad1.aWasPressed()) {
                shooter.toggle();
            }

            //if (gamepad1.bWasPressed()) {
                //intake.toggle();
            //}

            if(gamepad1.xWasPressed()){
                shooter.incrementLift(-0.1);
            }
            if (gamepad1.yWasPressed()){
                shooter.incrementLift(0.1);
            }
            if(gamepad1.rightBumperWasPressed()){
             shooter.toggleLift();
            }
            telemetry.update();
        }
    }

}
