// Made by @MrFearTick
// Hood Main
// 06/01/26

package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Amps;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hood extends SubsystemBase {
  final TalonFX hoodMotor1 = new TalonFX(10);
  final TalonFX hoodMotor2 = new TalonFX(11);

  final MotionMagicVoltage request = new MotionMagicVoltage(0);

  final MotionMagicConfigs MotionMagicConfig = new MotionMagicConfigs()
    .withMotionMagicCruiseVelocity(80)
    .withMotionMagicAcceleration(160)
    .withMotionMagicJerk(1600);

  final Slot0Configs PIDConfig = new Slot0Configs()
    .withKP(4.8)
    .withKI(0)
    .withKD(0.1);

  final TalonFXConfiguration motorConfigs = new TalonFXConfiguration()
    .withMotorOutput(new MotorOutputConfigs()
      .withInverted(InvertedValue.Clockwise_Positive)
      .withNeutralMode(NeutralModeValue.Brake))
    .withCurrentLimits(new CurrentLimitsConfigs()
      .withStatorCurrentLimit(Amps.of(120))
      .withStatorCurrentLimitEnable(true))
    .withFeedback(new FeedbackConfigs()
      .withSensorToMechanismRatio(20.0))
    .withMotionMagic(MotionMagicConfig)
    .withSlot0(PIDConfig);

  public Hood() {
   hoodMotor1.getConfigurator().apply(motorConfigs);
   hoodMotor2.getConfigurator().apply(motorConfigs);

   hoodMotor2.setControl(new Follower(hoodMotor1.getDeviceID(), false));
  }

  public void setHoodAngle(double angle) {
    double finalAngle = MathUtil.clamp(angle, 0, 80);

    hoodMotor1.setControl(request.withPosition(finalAngle));
    SmartDashboard.putNumber("Hood Position", hoodMotor1.getPosition().getValueAsDouble());
  }

  @Override
  public void periodic() {
  }
}
