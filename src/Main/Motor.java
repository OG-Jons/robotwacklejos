package Main;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Motor {
    private final EV3LargeRegulatedMotor motorL = new EV3LargeRegulatedMotor(MotorPort.A);
    private final EV3LargeRegulatedMotor motorR = new EV3LargeRegulatedMotor(MotorPort.B);

    public void driveStraightForward(int speed) {
        this.motorL.setSpeed(speed);
        this.motorR.setSpeed(speed);

        this.motorL.forward();
        this.motorR.forward();
    }

    public void driveCustomSpeed(int speedL, int speedR) {
        this.motorL.setSpeed(speedL);
        this.motorR.setSpeed(speedR);

        this.motorL.forward();
        this.motorR.forward();
    }

    public void floatMotors() {
        this.motorL.flt();
        this.motorR.flt();
    }

    public void stopMotors() {
        this.motorL.stop();
        this.motorR.stop();
    }
}
