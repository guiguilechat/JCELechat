package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class GallenteIndustrialBonusMiningHoldCapacity
    extends IntAttribute
{
    public static final GallenteIndustrialBonusMiningHoldCapacity INSTANCE = new GallenteIndustrialBonusMiningHoldCapacity();

    @Override
    public int getId() {
        return  3241;
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
        return "GallenteIndustrialBonusMiningHoldCapacity";
    }
}
