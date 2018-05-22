package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Multiplies EXPLOSIVE damage taken by Armor. 
 */
public class ShieldExplosiveDamageResonance
    extends DoubleAttribute
{
    public final static ShieldExplosiveDamageResonance INSTANCE = new ShieldExplosiveDamageResonance();

    @Override
    public int getId() {
        return  272;
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
        return  1.0;
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
        return "ShieldExplosiveDamageResonance";
    }
}
