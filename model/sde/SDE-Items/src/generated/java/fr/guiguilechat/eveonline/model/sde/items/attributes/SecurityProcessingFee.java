package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * ISK fee per tag to be paid when turning in a tag for a security-status gain
 */
public class SecurityProcessingFee
    extends IntAttribute
{
    public final static SecurityProcessingFee INSTANCE = new SecurityProcessingFee();

    @Override
    public int getId() {
        return  1904;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }
}
