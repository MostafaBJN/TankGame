package Thing;

import java.util.Random;

public class Degree {
    private static final int TURNING_DEGREE_VALUE = 18;
    private static final int DEFAULT_ANGLE = 0;
    private static final int FULL_CIRCLE_DEGREE = 360;

    private int angle;

    public Degree() {
        angle = randomAngle();
    }

    public Degree(int angle) {
        this.angle = angle;
    }

    public int randomAngle() {
        return new Random().nextInt(FULL_CIRCLE_DEGREE);
    }

    public void clockwiseTurning()
    {
        if(angle - TURNING_DEGREE_VALUE < 0)
        {
            angle = angle + FULL_CIRCLE_DEGREE;
        }
        angle = angle - TURNING_DEGREE_VALUE;
    }

    public void anticlockwiseTurning()
    {

        if(angle + TURNING_DEGREE_VALUE >= FULL_CIRCLE_DEGREE)
        {
            angle = angle + TURNING_DEGREE_VALUE - FULL_CIRCLE_DEGREE;
        }
        angle = angle + TURNING_DEGREE_VALUE;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}
