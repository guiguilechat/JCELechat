package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The amount of milliseconds before the object explodes.
 */
public class ExplosionDelay
    extends IntAttribute
{
    public static final ExplosionDelay INSTANCE = new ExplosionDelay();

    @Override
    public int getId() {
        return  281;
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
        return false;
    }

    @Override
    public String toString() {
        return "ExplosionDelay";
    }
}
