package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Set this attribute on a ship to declare that the ship is an eligible passenger to travel through an Industrial Jump Portal
 */
public class IsIndustrialJumpPortalPassenger
    extends IntAttribute
{
    public static final IsIndustrialJumpPortalPassenger INSTANCE = new IsIndustrialJumpPortalPassenger();

    @Override
    public int getId() {
        return  3325;
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
        return "IsIndustrialJumpPortalPassenger";
    }
}
