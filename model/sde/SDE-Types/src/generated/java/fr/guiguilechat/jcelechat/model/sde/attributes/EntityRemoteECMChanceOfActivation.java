package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Chance that the NPC remote ECM fires
 */
public class EntityRemoteECMChanceOfActivation
    extends IntAttribute
{
    public static final EntityRemoteECMChanceOfActivation INSTANCE = new EntityRemoteECMChanceOfActivation();

    @Override
    public int getId() {
        return  1664;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "EntityRemoteECMChanceOfActivation";
    }
}
