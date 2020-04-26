package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Activation chance for NPCGroupArmorAssist effect.
 */
public class EntityGroupArmorResistanceActivationChance
    extends IntAttribute
{
    public static final EntityGroupArmorResistanceActivationChance INSTANCE = new EntityGroupArmorResistanceActivationChance();

    @Override
    public int getId() {
        return  1682;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
        return "EntityGroupArmorResistanceActivationChance";
    }
}
