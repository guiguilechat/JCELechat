package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * bonus to range of tractor beams
 */
public class RoleBonusTractorBeamRange
    extends IntAttribute
{
    public static final RoleBonusTractorBeamRange INSTANCE = new RoleBonusTractorBeamRange();

    @Override
    public int getId() {
        return  1355;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "RoleBonusTractorBeamRange";
    }
}
