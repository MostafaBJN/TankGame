package Thing;

public class Prize
{
    //TYPES
    private static final int INVINCIBLE = 0;
    private static final int EXTRA_LIFE = 1;
    private static final int LASER = 2;
    private static final int POWER_SHOT_TWICE = 3;
    private static final int POWER_SHOT_TRIPLE = 4;


    int x;
    int y;



    char typeOfPrize;

    public Prize(char typeOfPrize2)
    {
        typeOfPrize = typeOfPrize2;
    }

}