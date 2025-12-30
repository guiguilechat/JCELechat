package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class DotMaxHPPercentagePerTickBonus
    extends IntAttribute
{
    public static final DotMaxHPPercentagePerTickBonus INSTANCE = new DotMaxHPPercentagePerTickBonus();

    @Override
    public int getId() {
        return  5749;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "DotMaxHPPercentagePerTickBonus";
    }
}
