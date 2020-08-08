package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * The chance that the customs official has of detecting contraband on board a scanned vessel
 */
public class ContrabandDetectionChance
    extends DoubleAttribute
{
    public static final ContrabandDetectionChance INSTANCE = new ContrabandDetectionChance();

    @Override
    public int getId() {
        return  723;
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
        return "ContrabandDetectionChance";
    }
}
