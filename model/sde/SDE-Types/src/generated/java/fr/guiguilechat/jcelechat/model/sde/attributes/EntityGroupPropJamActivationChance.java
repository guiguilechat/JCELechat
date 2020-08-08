package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Activation chance of NPCGroupPropJamAssist effect.
 */
public class EntityGroupPropJamActivationChance
    extends IntAttribute
{
    public static final EntityGroupPropJamActivationChance INSTANCE = new EntityGroupPropJamActivationChance();

    @Override
    public int getId() {
        return  1680;
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
        return "EntityGroupPropJamActivationChance";
    }
}
