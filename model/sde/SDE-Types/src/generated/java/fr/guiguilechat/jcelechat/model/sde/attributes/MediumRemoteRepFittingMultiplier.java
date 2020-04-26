package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class MediumRemoteRepFittingMultiplier
    extends IntAttribute
{
    public static final MediumRemoteRepFittingMultiplier INSTANCE = new MediumRemoteRepFittingMultiplier();

    @Override
    public int getId() {
        return  2691;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
        return "MediumRemoteRepFittingMultiplier";
    }
}
