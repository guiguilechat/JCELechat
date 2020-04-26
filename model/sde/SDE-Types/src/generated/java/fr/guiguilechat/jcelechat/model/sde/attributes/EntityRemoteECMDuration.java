package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The current duration for the remote ECM ( this is recalculated each time it is activated )
 */
public class EntityRemoteECMDuration
    extends IntAttribute
{
    public static final EntityRemoteECMDuration INSTANCE = new EntityRemoteECMDuration();

    @Override
    public int getId() {
        return  1658;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  300000.0;
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
        return "EntityRemoteECMDuration";
    }
}
