package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Duration for the NPCGroupShieldAssist effect
 */
public class EntityGroupShieldResistanceDuration
    extends IntAttribute
{
    public static final EntityGroupShieldResistanceDuration INSTANCE = new EntityGroupShieldResistanceDuration();

    @Override
    public int getId() {
        return  1672;
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
        return "EntityGroupShieldResistanceDuration";
    }
}
