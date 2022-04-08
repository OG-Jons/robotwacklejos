package Main;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class LineFollower {
    private static EV3LargeRegulatedMotor motorL;

    private static EV3LargeRegulatedMotor motorR;

    private static final EV3GyroSensor gyroSensor = new EV3GyroSensor(SensorPort.S3);

    private static EV3ColorSensor colorSensor;

    public static Direction currentDirection;

    public static int colorBefore;

    public static boolean wasOnBlack;

    public static boolean wasOnGreen = false;

    public static int rightTurn = 0;

    enum Direction {
        LEFT, RIGHT;
    }

    public static int rotation = 0;

    public static void main(String[] args) {
        motorL = new EV3LargeRegulatedMotor(MotorPort.A);
        motorR = new EV3LargeRegulatedMotor(MotorPort.B);
        colorSensor = new EV3ColorSensor(SensorPort.S4);
        currentDirection = Direction.LEFT;
        wasOnBlack = true;
        colorBefore = 7;
        int buttonId = 0;
        while (buttonId != 1) {
            if (Sensor.getDistance() < 30) {
                System.out.println("Robot is too close to something!");
                System.out.println(Sensor.getDistance());
                youShallNotPass();
                buttonId = Sensor.getTouchSensor();
            } else {
                buttonId = Sensor.getTouchSensor();
                rotation = readGyro();
                drive();
            }
        }
        closeComponents();
    }

    private static int readGyro() {
        final SampleProvider sp = gyroSensor.getAngleMode();
        float[] sample = new float[sp.sampleSize()];
        sp.fetchSample(sample, 0);
        return (int) sample[0];
    }

    private static void youShallNotPass() {
        motorR.stop();
        motorL.stop();
    }

    private static void closeComponents() {
        motorL.stop();
        motorR.stop();
        motorR.close();
        motorL.close();
    }

    private static void drive() {
        checkColor();
        if (currentDirection == Direction.LEFT) driveLeft();
        if (currentDirection == Direction.RIGHT) driveRight();
        colorBefore = colorSensor.getColorID();
    }

    private static void checkColor() {
        // White procedure
        if (colorSensor.getColorID() == 6 && wasOnBlack) {
            wasOnBlack = false;
            currentDirection = currentDirection == Direction.LEFT ? Direction.RIGHT : Direction.LEFT;
        }

        // Black procedure
        if (colorSensor.getColorID() == 7) {
            wasOnBlack = true;
        }

        // Red procedure
        if (colorSensor.getColorID() == 0) {
            // Turn 180 degrees using the gyroscope
            youShallNotPass();
            turnRightAndCheckForBlacks(-180);
            drive();
        }

        // Green procedure
        if (colorSensor.getColorID() == 1) {
            // Turn 180 degrees using the gyroscope
            youShallNotPass();
            turnRightAndCheckForBlacks(-180);
            drive();
            wasOnGreen = true;
        }

        // Yellow procedure
        if (colorSensor.getColorID() == 3) {
            // Should check the angles 90 and 270, and if there is a black line, go there
            youShallNotPass();
            driveForward();
            turnRightAndCheckForBlacks(-90);
            driveAndCheckForColor();
            if (colorSensor.getColorID() != 7) {
                turnAround(-180);
//                turnLeftAndCheckForBlacks(90);
//                driveAndCheckForColor();
//                if (colorSensor.getColorID() != 7) {
//                    turnLeftAndCheckForBlacks(90);
//                    driveAndCheckForColor();
//                }
            }
            drive();
        }


/*        // Green procedure
        if (colorSensor.getColorID() == 1) {
            // Stop and play a sound
            closeComponents();
        }*/
    }

    private static void turnRightAndCheckForBlacks(int turnLength) {
        gyroSensor.reset();
        int currentRotation = readGyro();
        while (currentRotation > turnLength && colorSensor.getColorID() != 7) {
            motorL.setSpeed(100);
            motorR.setSpeed(100);
            motorL.forward();
            motorR.backward();
            currentRotation = readGyro();
        }
        youShallNotPass();
    }

    private static void turnLeftAndCheckForBlacks(int turnLength) {
        gyroSensor.reset();
        int currentRotation = readGyro();
        while (currentRotation < turnLength && colorSensor.getColorID() != 7) {
            motorL.setSpeed(100);
            motorR.setSpeed(100);
            motorR.forward();
            motorL.backward();
            currentRotation = readGyro();
        }
        youShallNotPass();
    }

    private static void turnAround(int turnLength) {
        gyroSensor.reset();
        // Turn 180 degrees using the gyroscope
        int currentRotation = readGyro();
        while (currentRotation > turnLength) {
            motorL.setSpeed(100);
            motorR.setSpeed(100);
            motorL.forward();
            motorR.backward();
            currentRotation = readGyro();
        }
        youShallNotPass();
    }


    private static void driveLeft() {
        motorR.setSpeed(200);
        motorL.setSpeed(100);
        motorL.forward();
        motorR.forward();
        Delay.msDelay(100L);
    }

    private static void driveRight() {
        motorR.setSpeed(100);
        motorL.setSpeed(200);
        motorL.forward();
        motorR.forward();
        Delay.msDelay(100L);
    }

    private static void driveForward() {
        motorR.setSpeed(100);
        motorL.setSpeed(100);
        motorL.forward();
        motorR.forward();
        Delay.msDelay(500);
    }

    private static void driveBackwards() {
        motorR.setSpeed(100);
        motorL.setSpeed(100);
        motorL.backward();
        motorR.backward();
        Delay.msDelay(100);
    }

    private static void driveAndCheckForColor() {
        motorR.setSpeed(100);
        motorL.setSpeed(100);
        motorL.forward();
        motorR.forward();
        Delay.msDelay(100);
        if (colorSensor.getColorID() != 7) {
            youShallNotPass();
        } else {
            driveBackwards();
        }
    }
}

