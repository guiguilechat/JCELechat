package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class MediumRemoteRepFittingMultiplier
    extends IntAttribute
{
    public final static MediumRemoteRepFittingMultiplier INSTANCE = new MediumRemoteRepFittingMultiplier();

    @Override
    public int getId() {
        return  2691;
    }

    @Override
    public int getCatId() {
        return  1;
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
