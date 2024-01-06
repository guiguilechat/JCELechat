package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * DO NOT MESS WITH. The amount of damage done to the entities armor hit points. Starting armor damage.
 */
public class ArmorDamage
    extends IntAttribute
{
    public static final ArmorDamage INSTANCE = new ArmorDamage();

    @Override
    public int getId() {
        return  266;
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
        return "ArmorDamage";
    }
}
