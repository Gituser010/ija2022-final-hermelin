package ija.ija2022.homework2.game;

import ija.ija2022.homework2.common.Field;

public class GhostObject extends BasicObject {

    public GhostObject(Field f) {
        super(f);
    }

    @Override
    public boolean canMove(Field.Direction direction) {
        return true;
    }

    public boolean move(Field.Direction direction) {
        try {
            Field tmp = (Field) this.mainF.nextField(direction);
            tmp.put(this);
            this.mainF.remove(this);
            setField(tmp);
        } catch (UnsupportedOperationException exception) {
            System.err.println((exception.getMessage()));
            return false;
        }
        return true;
    }

    public boolean isGhost () {
        return true;
    }
}
