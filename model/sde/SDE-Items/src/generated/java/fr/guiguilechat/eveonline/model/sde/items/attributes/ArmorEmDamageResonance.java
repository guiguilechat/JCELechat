package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Multiplies EM damage taken by Armor. 
 */
public class ArmorEmDamageResonance
    extends DoubleAttribute
{
    public final static ArmorEmDamageResonance INSTANCE = new ArmorEmDamageResonance();

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
