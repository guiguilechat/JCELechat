package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Whether the structure requires the onliner's alliance to hold sovereignty in the system for it to be onlineable.
 */
public class OnliningRequiresSovereigntyLevel
    extends IntAttribute
{
    public static final OnliningRequiresSovereigntyLevel INSTANCE = new OnliningRequiresSovereigntyLevel();

    @Override
    public int getId() {
        return  1185;
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
        return true;
    }

    @Override
    public String toString() {
        return "OnliningRequiresSovereigntyLevel";
    }
}
