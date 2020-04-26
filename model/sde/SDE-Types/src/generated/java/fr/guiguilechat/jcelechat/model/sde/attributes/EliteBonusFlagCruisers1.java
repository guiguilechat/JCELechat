package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class EliteBonusFlagCruisers1
    extends IntAttribute
{
    public static final EliteBonusFlagCruisers1 INSTANCE = new EliteBonusFlagCruisers1();

    @Override
    public int getId() {
        return  2752;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
        return "EliteBonusFlagCruisers1";
    }
}
