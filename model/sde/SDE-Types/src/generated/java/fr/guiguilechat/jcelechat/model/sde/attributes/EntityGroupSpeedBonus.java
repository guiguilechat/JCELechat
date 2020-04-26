package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * amount of speed increase by NPCGroupSpeedAssist effect. Negative values is a bonus so e.g. -20 is a 20% bonus
 */
public class EntityGroupSpeedBonus
    extends IntAttribute
{
    public static final EntityGroupSpeedBonus INSTANCE = new EntityGroupSpeedBonus();

    @Override
    public int getId() {
        return  1674;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  0.0;
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
        return "EntityGroupSpeedBonus";
    }
}
