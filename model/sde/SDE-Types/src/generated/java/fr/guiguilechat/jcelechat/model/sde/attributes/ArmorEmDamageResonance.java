package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Multiplies EM damage taken by Armor. 
 */
public class ArmorEmDamageResonance
    extends DoubleAttribute
{
    public static final ArmorEmDamageResonance INSTANCE = new ArmorEmDamageResonance();

    @Override
    public int getId() {
        return  267;
    }

    @Override
    public int getCatId() {
        return  3;
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
        return "ArmorEmDamageResonance";
    }
}
