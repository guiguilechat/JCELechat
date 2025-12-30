package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Duration of NPCGroupArmorAssist effect.
 */
public class EntityGroupSpeedDuration
    extends IntAttribute
{
    public static final EntityGroupSpeedDuration INSTANCE = new EntityGroupSpeedDuration();

    @Override
    public int getId() {
        return  1677;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
        return "EntityGroupSpeedDuration";
    }
}
