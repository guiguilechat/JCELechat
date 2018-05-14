package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * reduction in MicroWarp Drive signature
 */
public class MWDSignatureRadiusBonus
    extends IntAttribute
{
    public final static MWDSignatureRadiusBonus INSTANCE = new MWDSignatureRadiusBonus();

    @Override
    public int getId() {
        return  1803;
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
}
