package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Damage multiplier.
 */
public class DamageMultiplier
    extends RealAttribute
{
    public static final DamageMultiplier INSTANCE = new DamageMultiplier();

    @Override
    public int getId() {
        return  64;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
        return "DamageMultiplier";
    }
}
