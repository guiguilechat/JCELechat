package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Kinetic damage done.
 */
public class KineticDamage
    extends RealAttribute
{
    public static final KineticDamage INSTANCE = new KineticDamage();

    @Override
    public int getId() {
        return  117;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
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
        return true;
    }

    @Override
    public String toString() {
        return "KineticDamage";
    }
}
