package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * If set on a charge or module type, will prevent it from being activated in empire space.
 */
public class DisallowInEmpireSpace
    extends IntAttribute
{
    public static final DisallowInEmpireSpace INSTANCE = new DisallowInEmpireSpace();

    @Override
    public int getId() {
        return  1074;
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
        return "DisallowInEmpireSpace";
    }
}
