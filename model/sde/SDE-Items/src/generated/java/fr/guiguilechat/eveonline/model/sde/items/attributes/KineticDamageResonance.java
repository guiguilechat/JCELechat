package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * damage multiplier vs. kinetic damagers.
 */
public class KineticDamageResonance
    extends DoubleAttribute
{
    public final static KineticDamageResonance INSTANCE = new KineticDamageResonance();

    @Override
    public int getId() {
        return  109;
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
        return false;
    }

    @Override
    public String toString() {
        return "KineticDamageResonance";
    }
}
