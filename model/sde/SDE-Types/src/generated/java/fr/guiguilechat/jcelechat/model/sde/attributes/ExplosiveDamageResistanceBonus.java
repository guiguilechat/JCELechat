package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class ExplosiveDamageResistanceBonus
    extends DoubleAttribute
{
    public static final ExplosiveDamageResistanceBonus INSTANCE = new ExplosiveDamageResistanceBonus();

    @Override
    public int getId() {
        return  985;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return false;
    }

    @Override
    public String toString() {
        return "ExplosiveDamageResistanceBonus";
    }
}
