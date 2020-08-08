package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class DamageMultiplierBonusMax
    extends DoubleAttribute
{
    public static final DamageMultiplierBonusMax INSTANCE = new DamageMultiplierBonusMax();

    @Override
    public int getId() {
        return  2734;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  0.5;
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
        return "DamageMultiplierBonusMax";
    }
}
