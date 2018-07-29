package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class ModifyTargetSpeedCapacitorNeed
    extends IntAttribute
{
    public final static ModifyTargetSpeedCapacitorNeed INSTANCE = new ModifyTargetSpeedCapacitorNeed();

    @Override
    public int getId() {
        return  515;
    }

    @Override
    public int getCatId() {
        return  9;
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
        return "ModifyTargetSpeedCapacitorNeed";
    }
}
