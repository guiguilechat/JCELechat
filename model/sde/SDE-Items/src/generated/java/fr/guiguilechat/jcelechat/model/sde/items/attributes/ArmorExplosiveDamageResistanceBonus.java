package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Explosive damage resistance bonus for armor
 */
public class ArmorExplosiveDamageResistanceBonus
    extends IntAttribute
{
    public final static ArmorExplosiveDamageResistanceBonus INSTANCE = new ArmorExplosiveDamageResistanceBonus();

    @Override
    public int getId() {
        return  1468;
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
        return "ArmorExplosiveDamageResistanceBonus";
    }
}
