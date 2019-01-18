package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Missile Damage Modifier. Smaller is better (Don't use less than 0.5)
 */
public class AoeDamageReductionFactor
    extends DoubleAttribute
{
    public static final AoeDamageReductionFactor INSTANCE = new AoeDamageReductionFactor();

    @Override
    public int getId() {
        return  1353;
    }

    @Override
    public int getCatId() {
        return  7;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
