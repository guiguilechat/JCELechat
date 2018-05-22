package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class PassiveShieldExplosiveDamageResonance
    extends DoubleAttribute
{
    public final static PassiveShieldExplosiveDamageResonance INSTANCE = new PassiveShieldExplosiveDamageResonance();

    @Override
    public int getId() {
        return  1422;
    }

    @Override
    public int getCatId() {
        return  2;
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
        return "PassiveShieldExplosiveDamageResonance";
    }
}
