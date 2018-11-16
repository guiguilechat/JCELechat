package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Precursor Battlecruiser Skill Attribute
 */
public class ShipBonusPBC1
    extends IntAttribute
{
    public final static ShipBonusPBC1 INSTANCE = new ShipBonusPBC1();

    @Override
    public int getId() {
        return  2801;
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
        return "ShipBonusPBC1";
    }
}
