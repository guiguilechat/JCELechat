package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Activation chance for NPCGroupSpeedAssist effect.
 */
public class EntityGroupSpeedActivationChance
    extends IntAttribute
{
    public static final EntityGroupSpeedActivationChance INSTANCE = new EntityGroupSpeedActivationChance();

    @Override
    public int getId() {
        return  1678;
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
        return "EntityGroupSpeedActivationChance";
    }
}
