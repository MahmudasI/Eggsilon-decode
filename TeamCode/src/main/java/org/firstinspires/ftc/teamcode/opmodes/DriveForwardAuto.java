package org.firstinspires.ftc.teamcode.opmodes;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.auto.pedroPathing.Constants;

@Autonomous(name = "Drive Forward Auto", group = "Autonomous")
public class DriveForwardAuto extends OpMode {

    private Follower follower;
    private Path forwardPath;

    // Starting position (x=0, y=0, heading=0 radians)
    private Pose startPose = new Pose(0, 0, 0);

    @Override
    public void init() {
        // Initialize the Follower using the Constants class
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startPose);

        // Build a path that drives forward 40 inches
        forwardPath = new Path(new BezierLine(
                startPose,
                new Pose(0, 40, 0)  // x=0, y=40 inches forward, heading=0
        ));
        forwardPath.setLinearHeadingInterpolation(
                startPose.getHeading(),
                startPose.getHeading()
        );

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void start() {
        follower.followPath(forwardPath);
    }

    @Override
    public void loop() {
        follower.update();

        telemetry.addData("X", follower.getPose().getX());
        telemetry.addData("Y", follower.getPose().getY());
        telemetry.addData("Heading", Math.toDegrees(follower.getPose().getHeading()));
        telemetry.addData("Following", follower.isBusy());
        telemetry.update();
    }

    @Override
    public void stop() {
        follower.breakFollowing();
    }
}