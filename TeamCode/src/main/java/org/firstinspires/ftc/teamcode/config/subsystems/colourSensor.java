package org.firstinspires.ftc.teamcode.config.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class colourSensor {
    NormalizedColorSensor colourSensor;

    public enum DetectedColour{
        GREEN,
        PURPLE,
        UNKNOW
    }

    public void init(HardwareMap hwMap){
        colourSensor = hwMap.get(NormalizedColorSensor.class, "colourSensor");
        colourSensor.setGain(4);
    }

    public DetectedColour getDetectedColour(Telemetry telemetry){
        NormalizedRGBA colors = colourSensor.getNormalizedColors();
        float normRed, normGreen, normBlue;
        normRed = colors.red / colors.alpha;
        normGreen = colors.green / colors.alpha;
        normBlue = colors.blue / colors.alpha;

        telemetry.addData("red", normRed);
        telemetry.addData("green", normGreen);
        telemetry.addData("blue", normBlue);

        return DetectedColour.UNKNOW;

    }
}
