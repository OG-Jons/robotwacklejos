package Main;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

public class Sensor {
    private static final EV3IRSensor ir1 = new EV3IRSensor(SensorPort.S2);
    private static final EV3TouchSensor ts1 = new EV3TouchSensor(SensorPort.S1);

    public static float getDistance() {
        final SampleProvider sp = ir1.getDistanceMode();
        int distanceValue = 0;

        float[] sample = new float[sp.sampleSize()];
        sp.fetchSample(sample, 0);
        distanceValue = (int) sample[0];
        return distanceValue;
    }

    public static int getTouchSensor() {
        final SampleProvider sp = ts1.getTouchMode();
        int touchValue = 0;
        float[] sample = new float[sp.sampleSize()];
        sp.fetchSample(sample, 0);
        touchValue = (int) sample[0];
        return touchValue;
    }

}
