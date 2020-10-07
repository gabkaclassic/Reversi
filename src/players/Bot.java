package players;

import java.util.Arrays;

public class Bot extends Gamer {

    private final int[] prioritiesCard = new int[SIZE_X * SIZE_Y];


    public Bot(Value[] field, Value val) {

        super(field, val);

        setPrioritiesCard();
    }

    protected int getIndex(int x, int y) {

        int max = -1;
        int index = 0;

        for(int ind: possibleMoves) {

            if(prioritiesCard[ind] >= max) {

                    max = prioritiesCard[ind];
                    index = ind;
            }
        }

        return index;
    }

    private void setPrioritiesCard() {

        Arrays.fill(prioritiesCard, 1);

        prioritiesCard[0] = prioritiesCard[SIZE_X - 1] =
                prioritiesCard[SIZE_X * SIZE_Y - SIZE_X] =
                        prioritiesCard[SIZE_X * SIZE_Y - 1] = 3;

        for(int i = 1; i < (SIZE_X - 1); i++) prioritiesCard[i] = 2;
        for(int i = (SIZE_X * 2 - 1); i < (SIZE_X * SIZE_Y - 1); i += SIZE_X) prioritiesCard[i] = 2;
        for(int i = (SIZE_X * SIZE_Y - SIZE_X + 1); i < (SIZE_X * SIZE_Y - 1); i++) prioritiesCard[i] = 2;
        for(int i = SIZE_X; i < (SIZE_X * SIZE_Y - SIZE_X); i += SIZE_X) prioritiesCard[i] = 2;

        for(int i = (SIZE_X + 1); i < (SIZE_X * 2 - 1); i++) prioritiesCard[i] = 0;
        for(int i = (SIZE_X * 2 - 2); i < (SIZE_X * SIZE_Y - 2); i += SIZE_X) prioritiesCard[i] = 0;
        for(int i = (SIZE_X * SIZE_Y - SIZE_X * 2 + 1); i < (SIZE_X * SIZE_Y - SIZE_X - 1); i++) prioritiesCard[i] = 0;
        for(int i = SIZE_X + 1; i < (SIZE_X * SIZE_Y - SIZE_X + 1); i += SIZE_X) prioritiesCard[i] = 0;
    }

}
