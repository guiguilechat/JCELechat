package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Chance of piercing the armor.
 */
public class ArmorPiercingChance
    extends IntAttribute
{
    public static final ArmorPiercingChance INSTANCE = new ArmorPiercingChance();

    @Override
    public int getId() {
        return  122;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  0.0;
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
        return "ArmorPiercingChance";
    }
}
