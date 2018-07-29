package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class PassiveArmorKineticDamageResonance
    extends DoubleAttribute
{
    public final static PassiveArmorKineticDamageResonance INSTANCE = new PassiveArmorKineticDamageResonance();

    @Override
    public int getId() {
        return  1420;
    }

    @Override
    public int getCatId() {
        return  3;
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
        return true;
    }

    @Override
    public String toString() {
        return "PassiveArmorKineticDamageResonance";
    }
}
