package players;

import ui.Panel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static players.Value.NULL;

public abstract class Gamer {

    protected static final int SIZE_X = Panel.getSizeX();
    protected static final int SIZE_Y = Panel.getSizeY();

    protected final List<Integer> possibleMoves = new ArrayList<>();

    protected Value[] field;

    protected Value value;
    protected int count = 0;

    public Gamer(Value[] field, Value val) {

        this.field = field;
        value = val;
    }

    public Value[] getField() {
        return field;
    }

    public Value getValue() {
        return value;
    }

    public int getCount() {
        return count;
    }

    public List<Integer> getPossibleMovies() {

        return possibleMoves;
    }

    public void move(int x, int y) {

        int index = getIndex(x, y);

        changePossibleMovies();

        if(possibleMoves.isEmpty()) {

            Panel.changeGamer();
            return;
        }

        if((field[index].equals(NULL)) && (possibleMoves.contains(index))) {

            Panel.decrementMovies();
            Panel.changeGamer();
            field[index] = value;
            repaintChips(index);
        }

        checkChips();
    }

    protected abstract int getIndex(int x, int y);

    private void checkChips() {

        count = 0;

        for(Value value: field)
            if(value.equals(this.value)) count++;
    }

    private void repaintChips(int index) {

        int[] steps = new int[] {1, -1, SIZE_X, -SIZE_X, SIZE_X + 1, SIZE_X - 1, -SIZE_X + 1, -SIZE_X - 1};

        for(int step: steps)
            if(checkLine(index, step))
                repaintLine(index, step);
    }

    private void repaintLine(int begin, int step) {

        for(int i = (begin + step); (i < field.length) && (i >= 0) && (!field[i].equals(value)); i += step)
            field[i] = value;
    }

    protected final void changePossibleMovies() {

        possibleMoves.clear();

        if(Panel.getMovies() > ((SIZE_Y * SIZE_X) - 4)) {

            if(field[27].equals(NULL)) possibleMoves.add(27);
            if(field[28].equals(NULL)) possibleMoves.add(28);
            if(field[35].equals(NULL)) possibleMoves.add(35);
            if(field[36].equals(NULL)) possibleMoves.add(36);

            return;
        }

        for(int i = 0; i < field.length; i++) {

            if(!field[i].equals(NULL)) continue;

            if(checkLine(i,1) || checkLine(i,-1) || checkLine(i, SIZE_X)
                    || checkLine(i, -SIZE_X) || checkLine(i,SIZE_X + 1)
                    || checkLine(i,SIZE_X - 1) || checkLine(i,-SIZE_X + 1)
                    || checkLine(i,-SIZE_X - 1)) possibleMoves.add(i);
        }
    }

    protected final boolean checkLine(int begin, int step) {

        Predicate<Integer> predicate;

        if((Math.abs(step) != SIZE_X) && (((step < 0) && (step != (-SIZE_X + 1)) && (begin % SIZE_X == 0))
                || ((step > 0) && (step != (SIZE_X - 1)) && (begin % SIZE_X == (SIZE_X - 1))))) return false;

            if(Math.abs(step) == 1) {

                if(step < 0) predicate = (i) -> ((i < field.length) && (i >= 0)
                        && ((i % SIZE_X) < (SIZE_X - 1)));
                else predicate = (i) -> ((i < field.length) && (i >= 0)
                            && ((i % SIZE_X) > 0));
        }
        else if(Math.abs(step) == SIZE_X) predicate = (i) -> ((i < field.length) && (i >= 0));
        else {

            if((step == (SIZE_X + 1)) || (step == (-SIZE_X + 1)))
                predicate = (i) -> ((i < field.length) && (i >= 0) && ((i % SIZE_X) != 0));
            else predicate = (i) -> ((i < field.length) && (i >= 0) && ((i % SIZE_X) < (SIZE_X - 1)));

        }

         for(int i = (begin + step); predicate.test(i); i += step) {

            if(field[i].equals(NULL)) return false;
            if(field[i].equals(value)) return (i != (begin + step));
        }

        return false;
    }
}
