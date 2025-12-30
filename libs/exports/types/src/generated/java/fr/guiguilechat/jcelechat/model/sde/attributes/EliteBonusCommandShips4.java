package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class EliteBonusCommandShips4
    extends IntAttribute
{
    public static final EliteBonusCommandShips4 INSTANCE = new EliteBonusCommandShips4();

    @Override
    public int getId() {
        return  5772;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  0.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "EliteBonusCommandShips4";
    }
}
