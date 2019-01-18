package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Torpedo velocity percent
 */
public class TorpedoVelocityPercent
    extends IntAttribute
{
    public static final TorpedoVelocityPercent INSTANCE = new TorpedoVelocityPercent();

    @Override
    public int getId() {
        return  404;
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
        return "TorpedoVelocityPercent";
    }
}
