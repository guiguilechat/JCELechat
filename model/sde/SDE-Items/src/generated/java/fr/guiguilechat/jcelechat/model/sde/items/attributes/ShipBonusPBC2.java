package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Precursor Battlecruiser Skill Attribute
 */
public class ShipBonusPBC2
    extends IntAttribute
{
    public static final ShipBonusPBC2 INSTANCE = new ShipBonusPBC2();

    @Override
    public int getId() {
        return  2802;
    }

    @Override
    public int getCatId() {
        return  9;
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
        return "ShipBonusPBC2";
    }
}
