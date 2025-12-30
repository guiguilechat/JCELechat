package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Grants the ability to open Jump Portals
 */
public class EnableOpenJumpPortal
    extends IntAttribute
{
    public static final EnableOpenJumpPortal INSTANCE = new EnableOpenJumpPortal();

    @Override
    public int getId() {
        return  3125;
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
        return "EnableOpenJumpPortal";
    }
}
