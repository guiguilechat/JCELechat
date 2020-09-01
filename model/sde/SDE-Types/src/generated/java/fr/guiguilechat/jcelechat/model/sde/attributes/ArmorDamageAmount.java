package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * An amount to modify the armor damage by.
 */
public class ArmorDamageAmount
    extends RealAttribute
{
    public static final ArmorDamageAmount INSTANCE = new ArmorDamageAmount();

    @Override
    public int getId() {
        return  84;
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
        return false;
    }

    @Override
    public String toString() {
        return "ArmorDamageAmount";
    }
}
