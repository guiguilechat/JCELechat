package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Chance of being caught Transporting contraband. 
 */
public class SmugglingChance
    extends RealAttribute
{
    public static final SmugglingChance INSTANCE = new SmugglingChance();

    @Override
    public int getId() {
        return  445;
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
        return "SmugglingChance";
    }
}
