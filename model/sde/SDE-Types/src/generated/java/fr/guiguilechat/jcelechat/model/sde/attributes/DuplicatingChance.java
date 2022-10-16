package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Chance of upgrading a module to next tech level 
 */
public class DuplicatingChance
    extends IntAttribute
{
    public static final DuplicatingChance INSTANCE = new DuplicatingChance();

    @Override
    public int getId() {
        return  399;
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
        return "DuplicatingChance";
    }
}
