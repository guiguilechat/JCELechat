package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Chance of entity warp scrambling it's target.
 */
public class EntityWarpScrambleChance
    extends IntAttribute
{
    public final static EntityWarpScrambleChance INSTANCE = new EntityWarpScrambleChance();

    @Override
    public int getId() {
        return  504;
    }

    @Override
    public int getCatId() {
        return  27;
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
}
