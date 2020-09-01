package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Explosive damage done.
 */
public class ExplosiveDamage
    extends RealAttribute
{
    public static final ExplosiveDamage INSTANCE = new ExplosiveDamage();

    @Override
    public int getId() {
        return  116;
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
        return "ExplosiveDamage";
    }
}
