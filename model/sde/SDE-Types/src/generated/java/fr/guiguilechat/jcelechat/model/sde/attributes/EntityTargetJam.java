package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Target Jam multiplier on max locked targets for NPCs
 */
public class EntityTargetJam
    extends IntAttribute
{
    public static final EntityTargetJam INSTANCE = new EntityTargetJam();

    @Override
    public int getId() {
        return  928;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "EntityTargetJam";
    }
}
