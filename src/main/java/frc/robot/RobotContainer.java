package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hood;

public class RobotContainer {
  public final Hood hood = new Hood();

  public RobotContainer() {}
  
  public Command getAutonomousCommand() {
    return null;
  }
}
