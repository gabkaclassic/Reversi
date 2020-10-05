package players;

import ui.Panel;

public class Player extends Gamer {

    private static final int SIZE_EDGE = Panel.getSizeEdge();

    public Player(Value[] field, Value val) {

        super(field, val);
    }

    protected int getIndex(int x, int y) {

        int result = (SIZE_X * (y / SIZE_EDGE)) + (x / SIZE_EDGE);

        if(result >= field.length) throw new IllegalArgumentException("Invalid index");

        return result;
    }
}
