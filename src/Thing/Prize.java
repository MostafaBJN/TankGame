package Thing;


import java.awt.image.BufferedImage;
import java.io.Serializable;

import static Thing.Image.Prize.*;

public class Prize implements Serializable, Thing
{


    private int style;
    private transient BufferedImage styleImage;

    int x;
    int y;



    char typeOfPrize;

    public Prize(char typeOfPrize2)
    {
        typeOfPrize = typeOfPrize2;
    }

    /**
     * Find the Image Of This Ground
     */
    public void styleFinder() {
        switch (style) {
            case SHIELD -> styleImage = SHIELD_IMAGE;
            case HEALTH_INCREASE -> styleImage = HEALTH_INCREASE_IMAGE;
            case LASER -> styleImage = LASER_IMAGE;
            case POWER_SHOT_TWICE -> styleImage = POWER_SHOT_TWICE_IMAGE;
            case POWER_SHOT_TRIPLE -> styleImage = POWER_SHOT_TRIPLE_IMAGE;
        }
    }

    @Override
    public void setStyle(int style) {

    }

}
