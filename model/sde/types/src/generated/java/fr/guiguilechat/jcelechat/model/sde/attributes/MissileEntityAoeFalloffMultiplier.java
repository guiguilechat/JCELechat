package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class MissileEntityAoeFalloffMultiplier
    extends IntAttribute
{
    public static final MissileEntityAoeFalloffMultiplier INSTANCE = new MissileEntityAoeFalloffMultiplier();

    @Override
    public int getId() {
        return  860;
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
        return "MissileEntityAoeFalloffMultiplier";
    }
}
