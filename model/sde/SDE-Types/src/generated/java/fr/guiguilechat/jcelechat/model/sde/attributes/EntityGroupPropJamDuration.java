package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Duration of NPCGroupPropJamAssist effect.
 */
public class EntityGroupPropJamDuration
    extends IntAttribute
{
    public static final EntityGroupPropJamDuration INSTANCE = new EntityGroupPropJamDuration();

    @Override
    public int getId() {
        return  1679;
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
        return "EntityGroupPropJamDuration";
    }
}
