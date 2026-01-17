package frc.robot.subsystems;
//Imports(figure it out Einstein)
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import static frc.robot.Constants.shooterConstants.*;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.SparkMaxConfig;


public class Shooter extends SubsystemBase {
  //defininine
  private SparkMax motorShooter;
  private SparkMaxConfig motorShooterConfig;
  private SparkClosedLoopController sparkControl;
  private RelativeEncoder encoder;
  
  public Shooter() {
    //define motor and its controller and their effects.(/°W^)
    motorShooter = new SparkMax(SHOOTER, MotorType.kBrushless);
    motorShooterConfig = new SparkMaxConfig();
    sparkControl = motorShooter.getClosedLoopController();
    encoder = motorShooter.getEncoder();
//PID woot! woot!
    motorShooterConfig
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(60)
      .closedLoop.positionWrappingEnabled(true)
      .pid(.005, 0, 0);
    motorShooterConfig.encoder.velocityConversionFactor(VELOCITY_CONVERT);
//config
    motorShooter.configure(motorShooterConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }
public void stop(){
  // We need to put a stop to this
  motorShooter.stopMotor();
}
public void spinShoot(){
  //largely an empty command just here to prevent an ERROR
  motorShooter.set(.4);
}
public void PIDShoot(double fireSpeed){
  //here is what casues the shot
  sparkControl.setSetpoint(fireSpeed, ControlType.kVelocity);
}
  @Override
  public void periodic() {
  }
}
