package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


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
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
