package org.firstinspires.ftc.teamcode.config.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Sorting extends SubsystemBase {
    Servo sorting;
    private double SortPos;

    double Starting_Sort_Pos = 0.5;
    double Starting_Lift_Pos = 0.25; // neisivaizduoju kaip veiks

    double SortTurn;

    boolean IsSortingSorting;

    public Sorting(HardwareMap hardwareMap, Telemetry telemetry) {
        sorting = hardwareMap.get(Servo.class, "sorting");
        IsSortingSorting = false;
        SortPos();
    }
        public void setSortPos(double pos) {
            this.SortPos = pos;
            sorting.setPosition(SortPos);
        }

        public void  incrementSort(double change){
            double mappedChange = Math.min(1, Math.max(0, SortPos + change));
            setSortPos(mappedChange);
            this.SortPos = mappedChange;
        }
        public void raiseLift(){
            //setLiftPos(SHOOTING_LIFT_POS);
            //isLiftUp = true;
        }
        public  void SortPos(){
            setSortPos(Starting_Lift_Pos);
            IsSortingSorting = false;
        }
        //public void toggleLift(){
           // if (!isLiftUp){
                //raiseLift();
            //} else {
                //lowerLift();
           // }

}





