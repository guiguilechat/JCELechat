package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Missiles velocity multiplier
 */
public class MissileStandardVelocityPecent
    extends IntAttribute
{
    public final static MissileStandardVelocityPecent INSTANCE = new MissileStandardVelocityPecent();

    @Override
    public int getId() {
        return  400;
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
        return  100.0;
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
        return "MissileStandardVelocityPecent";
    }
}
