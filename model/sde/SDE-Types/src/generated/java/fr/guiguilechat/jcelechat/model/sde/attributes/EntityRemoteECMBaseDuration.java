package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The base time between ECM bursts
 */
public class EntityRemoteECMBaseDuration
    extends IntAttribute
{
    public static final EntityRemoteECMBaseDuration INSTANCE = new EntityRemoteECMBaseDuration();

    @Override
    public int getId() {
        return  1661;
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
        return "EntityRemoteECMBaseDuration";
    }
}
