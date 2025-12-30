package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The base number of players, where the scaling of the remote ECM should start
 */
public class EntityRemoteECMIntendedNumPlayers
    extends IntAttribute
{
    public static final EntityRemoteECMIntendedNumPlayers INSTANCE = new EntityRemoteECMIntendedNumPlayers();

    @Override
    public int getId() {
        return  1663;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  40.0;
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
        return "EntityRemoteECMIntendedNumPlayers";
    }
}
