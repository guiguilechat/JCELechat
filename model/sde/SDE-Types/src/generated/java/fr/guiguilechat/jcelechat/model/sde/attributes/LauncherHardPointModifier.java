package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class LauncherHardPointModifier
    extends IntAttribute
{
    public static final LauncherHardPointModifier INSTANCE = new LauncherHardPointModifier();

    @Override
    public int getId() {
        return  1369;
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
        return "LauncherHardPointModifier";
    }
}
