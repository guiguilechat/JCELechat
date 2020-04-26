package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class KineticDamageResistanceBonus
    extends DoubleAttribute
{
    public static final KineticDamageResistanceBonus INSTANCE = new KineticDamageResistanceBonus();

    @Override
    public int getId() {
        return  986;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public double getDefaultValue() {
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
        return "KineticDamageResistanceBonus";
    }
}
