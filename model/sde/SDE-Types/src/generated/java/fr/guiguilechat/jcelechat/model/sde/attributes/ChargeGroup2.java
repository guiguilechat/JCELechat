package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * One of the groups of charge this launcher can be loaded with.
 */
public class ChargeGroup2
    extends IntAttribute
{
    public static final ChargeGroup2 INSTANCE = new ChargeGroup2();

    @Override
    public int getId() {
        return  605;
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
        return "ChargeGroup2";
    }
}
