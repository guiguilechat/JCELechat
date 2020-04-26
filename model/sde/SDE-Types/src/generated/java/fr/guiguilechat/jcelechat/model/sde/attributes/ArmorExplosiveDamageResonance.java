package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Multiplies EXPLOSIVE damage taken by Armor. 
 */
public class ArmorExplosiveDamageResonance
    extends DoubleAttribute
{
    public static final ArmorExplosiveDamageResonance INSTANCE = new ArmorExplosiveDamageResonance();

    @Override
    public int getId() {
        return  268;
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
        return "ArmorExplosiveDamageResonance";
    }
}
