package Thing;

public class Prize
{
    //TYPES
    private static final int PROTECTOR = 0;
    private static final int LAZARE = 1;
    private static final int EXTRA_LIFE = 2;
    private static final int POWER_SHOT_TWICE = 3;
    private static final int POWER_SHOT_TRIPLE = 4;




    char typeOfPrize;

    public Prize(char typeOfPrize2)
    {
        typeOfPrize = typeOfPrize2;
    }

    public static void pickedPrize (Prize prize, Tank prizeWinnerTank) {
        prizeWinnerTank.setCurrentPrize(prize);
    }
}
