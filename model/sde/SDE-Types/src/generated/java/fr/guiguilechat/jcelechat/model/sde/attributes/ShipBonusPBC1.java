package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Precursor Battlecruiser Skill Attribute
 */
public class ShipBonusPBC1
    extends IntAttribute
{
    public static final ShipBonusPBC1 INSTANCE = new ShipBonusPBC1();

    @Override
    public int getId() {
        return  2801;
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
