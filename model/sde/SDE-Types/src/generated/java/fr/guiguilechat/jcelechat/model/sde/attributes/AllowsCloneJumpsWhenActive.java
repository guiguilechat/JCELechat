package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * If this module is active and the ship supports it, the ship can serve as a destination for clone jumps.
 */
public class AllowsCloneJumpsWhenActive
    extends IntAttribute
{
    public static final AllowsCloneJumpsWhenActive INSTANCE = new AllowsCloneJumpsWhenActive();

    @Override
    public int getId() {
        return  981;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "AllowsCloneJumpsWhenActive";
    }
}
