package Main;

import lejos.hardware.lcd.LCD;

public class Main {

    private static final int TURN_OFF_VALUE = 1;
    private static final int DEFAULT_DELAY = 100;

    private static final int WHITE = 6;
    private static final int BLACK = 7;
    private static final int BLUE = 2;
    private static final int GREEN = 1;
    private static final int RED = 0;
    private static final int YELLOW = 3;

    private static final Motor motors = new Motor();

    public static void stopProgram() {
        motors.stopMotors();
        System.exit(1);
    }

    public static void main(String[] args) {

//        while (Sensor.getTouchSensor() != TURN_OFF_VALUE) {
//
//            switch (Sensor.getCurrentColor()) {
//                case BLACK:
//                    LCD.drawString("Black", 0, 4);
//                    motors.driveCustomSpeed(200, 400);
//                    // motors.floatMotors();
//                    try {
//                        Thread.sleep(DEFAULT_DELAY);
//                    } catch (InterruptedException e) {
//                        System.out.println("Motorthread was interrupted!!");
//                    }
//                    LCD.clear();
//                    break;
//
//                case WHITE:
//                    LCD.drawString("White", 0, 4);
//                    motors.driveCustomSpeed(400, 200);
//                    // motors.floatMotors();
//                    try {
//                        Thread.sleep(DEFAULT_DELAY);
//                    } catch (InterruptedException e) {
//                        System.out.println("Motorthread was interrupted!!");
//                    }
//                    LCD.clear();
//                    break;
//
//                case BLUE:
//                    LCD.drawString("Blue", 0, 4);
//                    break;
//
//                case GREEN:
//                    LCD.drawString("Green", 0, 4);
//                    break;
//
//                case RED:
//                    LCD.drawString("Red", 0, 4);
//                    break;
//
//                case YELLOW:
//                    LCD.drawString("Yellow", 0, 4);
//                    break;
//
//                default:
//                    LCD.drawString("Unknown", 0, 4);
//                    motors.stopMotors();
//                    break;
//            }
//            motors.stopMotors();
//        }

        stopProgram();


    }

}
