package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Lower cap, so even if more players are added to the bubble remote ECM duration will not go below this value
 */
public class EntityRemoteECMMinDuration
    extends IntAttribute
{
    public static final EntityRemoteECMMinDuration INSTANCE = new EntityRemoteECMMinDuration();

    @Override
    public int getId() {
        return  1659;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  10000.0;
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
        return "EntityRemoteECMMinDuration";
    }
}
