package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Range of the explosion when the ship dies.
 */
public class OnDeathAOERadius
    extends IntAttribute
{
    public final static OnDeathAOERadius INSTANCE = new OnDeathAOERadius();

    @Override
    public int getId() {
        return  2275;
    }

    @Override
    public int getCatId() {
        return  41;
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
        return "OnDeathAOERadius";
    }
}
