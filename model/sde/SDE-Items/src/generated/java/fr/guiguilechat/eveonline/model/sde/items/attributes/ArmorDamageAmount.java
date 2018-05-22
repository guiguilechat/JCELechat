package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * An amount to modify the armor damage by.
 */
public class ArmorDamageAmount
    extends IntAttribute
{
    public final static ArmorDamageAmount INSTANCE = new ArmorDamageAmount();

    @Override
    public int getId() {
        return  84;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return false;
    }

    @Override
    public String toString() {
        return "ArmorDamageAmount";
    }
}
