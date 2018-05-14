package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Multiplies KINETIC damage taken by Armor. 
 */
public class ShieldKineticDamageResonance
    extends DoubleAttribute
{
    public final static ShieldKineticDamageResonance INSTANCE = new ShieldKineticDamageResonance();

    @Override
    public int getId() {
        return  273;
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
}
