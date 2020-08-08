package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus applied to all NPCs in the same group. Used by the NPCGroupShieldAssist effect. Negative values is a bonus so e.g. -20 is a 20% bonus
 */
public class EntityGroupShieldResistanceBonus
    extends IntAttribute
{
    public static final EntityGroupShieldResistanceBonus INSTANCE = new EntityGroupShieldResistanceBonus();

    @Override
    public int getId() {
        return  1671;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
        return "EntityGroupShieldResistanceBonus";
    }
}
