package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * One of the groups of charge this launcher can be loaded with.
 */
public class ChargeGroup3
    extends IntAttribute
{
    public static final ChargeGroup3 INSTANCE = new ChargeGroup3();

    @Override
    public int getId() {
        return  606;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ChargeGroup3";
    }
}
