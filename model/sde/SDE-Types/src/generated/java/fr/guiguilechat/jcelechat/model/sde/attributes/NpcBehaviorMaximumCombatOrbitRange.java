package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Used by Behavior NPCs to work out minimum orbit range. If the npc has an effect with a shorter range, it will use the effects range instead.
 */
public class NpcBehaviorMaximumCombatOrbitRange
    extends IntAttribute
{
    public static final NpcBehaviorMaximumCombatOrbitRange INSTANCE = new NpcBehaviorMaximumCombatOrbitRange();

    @Override
    public int getId() {
        return  2786;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "NpcBehaviorMaximumCombatOrbitRange";
    }
}
