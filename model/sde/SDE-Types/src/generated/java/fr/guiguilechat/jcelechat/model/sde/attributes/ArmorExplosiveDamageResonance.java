package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Multiplies EXPLOSIVE damage taken by Armor. 
 */
public class ArmorExplosiveDamageResonance
    extends RealAttribute
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
        return "ArmorExplosiveDamageResonance";
    }
}
