package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Bonus for scan resolution
 */
public class ScanResolutionBonus
    extends RealAttribute
{
    public static final ScanResolutionBonus INSTANCE = new ScanResolutionBonus();

    @Override
    public int getId() {
        return  566;
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
        return false;
    }

    @Override
    public String toString() {
        return "ScanResolutionBonus";
    }
}
