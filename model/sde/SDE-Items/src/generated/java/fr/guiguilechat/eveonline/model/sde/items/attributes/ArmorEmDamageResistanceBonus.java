package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * EM damage resistance bonus for armor
 */
public class ArmorEmDamageResistanceBonus
    extends IntAttribute
{
    public final static ArmorEmDamageResistanceBonus INSTANCE = new ArmorEmDamageResistanceBonus();

    @Override
    public int getId() {
        return  1465;
    }

    @Override
    public int getCatId() {
        return  3;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
        return "ArmorEmDamageResistanceBonus";
    }
}
