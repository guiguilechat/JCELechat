package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Kinetic damage resistance bonus for armor
 */
public class ArmorKineticDamageResistanceBonus
    extends IntAttribute
{
    public final static ArmorKineticDamageResistanceBonus INSTANCE = new ArmorKineticDamageResistanceBonus();

    @Override
    public int getId() {
        return  1466;
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
        return "ArmorKineticDamageResistanceBonus";
    }
}
