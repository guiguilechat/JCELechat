package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * The chance that the customs official has of detecting contraband on board a scanned vessel
 */
public class ContrabandDetectionChance
    extends DoubleAttribute
{
    public final static ContrabandDetectionChance INSTANCE = new ContrabandDetectionChance();

    @Override
    public int getId() {
        return  723;
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
        return "ContrabandDetectionChance";
    }
}
