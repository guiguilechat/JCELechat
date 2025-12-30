package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * One of the groups of launcher this charge can be loaded into.
 */
public class LauncherGroup
    extends IntAttribute
{
    public static final LauncherGroup INSTANCE = new LauncherGroup();

    @Override
    public int getId() {
        return  137;
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
        return "LauncherGroup";
    }
}
