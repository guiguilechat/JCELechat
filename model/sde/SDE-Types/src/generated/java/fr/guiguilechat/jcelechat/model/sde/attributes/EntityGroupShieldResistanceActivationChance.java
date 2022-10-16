package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Activation chance of the NPCGroupShieldAssist effect
 */
public class EntityGroupShieldResistanceActivationChance
    extends IntAttribute
{
    public static final EntityGroupShieldResistanceActivationChance INSTANCE = new EntityGroupShieldResistanceActivationChance();

    @Override
    public int getId() {
        return  1673;
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
        return "EntityGroupShieldResistanceActivationChance";
    }
}
