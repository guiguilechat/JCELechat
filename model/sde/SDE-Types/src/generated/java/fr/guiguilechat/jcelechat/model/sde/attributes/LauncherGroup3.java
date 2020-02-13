package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * One of the groups of launcher this charge can be loaded into.
 */
public class LauncherGroup3
    extends IntAttribute
{
    public static final LauncherGroup3 INSTANCE = new LauncherGroup3();

    @Override
    public int getId() {
        return  603;
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
        return "LauncherGroup3";
    }
}
