package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This attribute is used on entities to link them to a player ship group. This is then used to determine which overview icon they should get, among other things
 */
public class EntityOverviewShipGroupId
    extends IntAttribute
{
    public static final EntityOverviewShipGroupId INSTANCE = new EntityOverviewShipGroupId();

    @Override
    public int getId() {
        return  1766;
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
        return "EntityOverviewShipGroupId";
    }
}
