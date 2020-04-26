package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Amount of armor resistance bonus. Used by NPCGroupArmorAssist. Negative values is a bonus so e.g. -20 is a 20% bonus
 */
public class EntityGroupArmorResistanceBonus
    extends IntAttribute
{
    public static final EntityGroupArmorResistanceBonus INSTANCE = new EntityGroupArmorResistanceBonus();

    @Override
    public int getId() {
        return  1676;
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
        return "EntityGroupArmorResistanceBonus";
    }
}
