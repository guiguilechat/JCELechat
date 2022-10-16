package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The number of players in each step of scaling of remote ECM
 */
public class EntityRemoteECMExtraPlayerScale
    extends IntAttribute
{
    public static final EntityRemoteECMExtraPlayerScale INSTANCE = new EntityRemoteECMExtraPlayerScale();

    @Override
    public int getId() {
        return  1662;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  8.0;
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
        return "EntityRemoteECMExtraPlayerScale";
    }
}
