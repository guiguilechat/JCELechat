package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class PassiveArmorExplosiveDamageResonance
    extends DoubleAttribute
{
    public final static PassiveArmorExplosiveDamageResonance INSTANCE = new PassiveArmorExplosiveDamageResonance();

    @Override
    public int getId() {
        return  1421;
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
}
