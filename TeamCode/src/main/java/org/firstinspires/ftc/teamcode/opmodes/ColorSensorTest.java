package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.config.subsystems.colourSensor;

@TeleOp(group = "", name = "ColourTest")
public class ColorSensorTest extends OpMode{

    colourSensor bench = new colourSensor();

    @Override
    public void init(){
        bench.init(hardwareMap);
    }
    @Override
    public void loop(){
        bench.getDetectedColour(telemetry);
    }
}
