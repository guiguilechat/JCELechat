package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Chance of NPC effect to be activated each duration
 */
public class NpcTrackingDisruptorActivationChance
    extends RealAttribute
{
    public static final NpcTrackingDisruptorActivationChance INSTANCE = new NpcTrackingDisruptorActivationChance();

    @Override
    public int getId() {
        return  933;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "NpcTrackingDisruptorActivationChance";
    }
}
