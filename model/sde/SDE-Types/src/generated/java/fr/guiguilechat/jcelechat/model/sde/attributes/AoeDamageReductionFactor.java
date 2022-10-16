package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Missile Damage Modifier. Smaller is better (Don't use less than 0.5)
 */
public class AoeDamageReductionFactor
    extends RealAttribute
{
    public static final AoeDamageReductionFactor INSTANCE = new AoeDamageReductionFactor();

    @Override
    public int getId() {
        return  1353;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "AoeDamageReductionFactor";
    }
}
