package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class BattleshipExtenderHPBonus
    extends IntAttribute
{
    public static final BattleshipExtenderHPBonus INSTANCE = new BattleshipExtenderHPBonus();

    @Override
    public int getId() {
        return  3267;
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
        return "BattleshipExtenderHPBonus";
    }
}
