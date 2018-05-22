package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Heavy missile speed percent
 */
public class HeavyMissileSpeedPercent
    extends IntAttribute
{
    public final static HeavyMissileSpeedPercent INSTANCE = new HeavyMissileSpeedPercent();

    @Override
    public int getId() {
        return  402;
    }

    @Override
    public int getCatId() {
        return  9;
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
        return "HeavyMissileSpeedPercent";
    }
}
