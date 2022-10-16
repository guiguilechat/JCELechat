package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class StealthBomberLauncherCPU
    extends RealAttribute
{
    public static final StealthBomberLauncherCPU INSTANCE = new StealthBomberLauncherCPU();

    @Override
    public int getId() {
        return  2732;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "StealthBomberLauncherCPU";
    }
}
