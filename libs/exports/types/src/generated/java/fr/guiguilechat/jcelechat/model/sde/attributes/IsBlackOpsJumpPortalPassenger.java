package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Set this attribute on a ship to declare that the ship is an eligible passenger to travel through a BlackOps Jump Portal
 */
public class IsBlackOpsJumpPortalPassenger
    extends IntAttribute
{
    public static final IsBlackOpsJumpPortalPassenger INSTANCE = new IsBlackOpsJumpPortalPassenger();

    @Override
    public int getId() {
        return  3320;
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
        return false;
    }

    @Override
    public String toString() {
        return "IsBlackOpsJumpPortalPassenger";
    }
}
