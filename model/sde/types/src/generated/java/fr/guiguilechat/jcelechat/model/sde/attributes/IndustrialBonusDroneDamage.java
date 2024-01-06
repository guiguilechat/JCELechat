package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class IndustrialBonusDroneDamage
    extends IntAttribute
{
    public static final IndustrialBonusDroneDamage INSTANCE = new IndustrialBonusDroneDamage();

    @Override
    public int getId() {
        return  2580;
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
        return "IndustrialBonusDroneDamage";
    }
}
