package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Set this attribute on a ship to declare that the ship is an eligible passenger to travel through a Titan Jump Portal
 */
public class IsTitanJumpPortalPassenger
    extends IntAttribute
{
    public static final IsTitanJumpPortalPassenger INSTANCE = new IsTitanJumpPortalPassenger();

    @Override
    public int getId() {
        return  3319;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "IsTitanJumpPortalPassenger";
    }
}
