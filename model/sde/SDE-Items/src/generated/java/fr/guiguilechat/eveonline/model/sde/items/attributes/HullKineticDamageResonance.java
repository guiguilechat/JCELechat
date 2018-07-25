package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class HullKineticDamageResonance
    extends DoubleAttribute
{
    public final static HullKineticDamageResonance INSTANCE = new HullKineticDamageResonance();

    @Override
    public int getId() {
        return  976;
    }

    @Override
    public int getCatId() {
        return  4;
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
        return true;
    }

    @Override
    public String toString() {
        return "HullKineticDamageResonance";
    }
}