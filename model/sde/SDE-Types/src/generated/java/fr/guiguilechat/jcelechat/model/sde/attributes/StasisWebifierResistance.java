package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Resistance against Stasis Webifiers
 */
public class StasisWebifierResistance
    extends DoubleAttribute
{
    public static final StasisWebifierResistance INSTANCE = new StasisWebifierResistance();

    @Override
    public int getId() {
        return  2115;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return true;
    }

    @Override
    public String toString() {
        return "StasisWebifierResistance";
    }
}
