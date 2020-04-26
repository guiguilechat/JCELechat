package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Amount of propulsion jamming assist. Used by NPCGroupPropJamAssist. Negative values is a bonus so e.g. -20 is a 20% bonus
 */
public class EntityGroupPropJamBonus
    extends IntAttribute
{
    public static final EntityGroupPropJamBonus INSTANCE = new EntityGroupPropJamBonus();

    @Override
    public int getId() {
        return  1675;
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
        return "EntityGroupPropJamBonus";
    }
}
