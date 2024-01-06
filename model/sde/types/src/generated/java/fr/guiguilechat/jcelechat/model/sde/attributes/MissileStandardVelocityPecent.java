package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Missiles velocity multiplier
 */
public class MissileStandardVelocityPecent
    extends IntAttribute
{
    public static final MissileStandardVelocityPecent INSTANCE = new MissileStandardVelocityPecent();

    @Override
    public int getId() {
        return  400;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
