package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Multiplies EM damage taken by Armor. 
 */
public class ArmorEmDamageResonance
    extends RealAttribute
{
    public static final ArmorEmDamageResonance INSTANCE = new ArmorEmDamageResonance();

    @Override
    public int getId() {
        return  267;
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
        return "ArmorEmDamageResonance";
    }
}
