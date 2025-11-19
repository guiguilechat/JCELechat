package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * The scaling factor used for the NPC remote ECM
 */
public class EntityRemoteECMDurationScale
    extends RealAttribute
{
    public static final EntityRemoteECMDurationScale INSTANCE = new EntityRemoteECMDurationScale();

    @Override
    public int getId() {
        return  1660;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  0.9;
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
        return "EntityRemoteECMDurationScale";
    }
}
