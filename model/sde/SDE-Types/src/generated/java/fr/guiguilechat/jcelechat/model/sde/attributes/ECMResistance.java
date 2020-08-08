package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Resistance to ECM. 0 gives Immunity.
 */
public class ECMResistance
    extends DoubleAttribute
{
    public static final ECMResistance INSTANCE = new ECMResistance();

    @Override
    public int getId() {
        return  2253;
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
        return "ECMResistance";
    }
}
