package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class BattleshipPlateHPBonus
    extends IntAttribute
{
    public static final BattleshipPlateHPBonus INSTANCE = new BattleshipPlateHPBonus();

    @Override
    public int getId() {
        return  3266;
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
        return "BattleshipPlateHPBonus";
    }
}
