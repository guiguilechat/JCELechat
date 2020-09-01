package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Chance of entity warp scrambling it's target.
 */
public class EntityWarpScrambleChance
    extends RealAttribute
{
    public static final EntityWarpScrambleChance INSTANCE = new EntityWarpScrambleChance();

    @Override
    public int getId() {
        return  504;
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
        return "EntityWarpScrambleChance";
    }
}
