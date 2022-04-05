package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Set this attribute on a ship to declare that the ship is an eligible passenger to be carried through a BlackOps Jump Conduit
 */
public class IsBlackOpsJumpConduitPassenger
    extends IntAttribute
{
    public static final IsBlackOpsJumpConduitPassenger INSTANCE = new IsBlackOpsJumpConduitPassenger();

    @Override
    public int getId() {
        return  3322;
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
        return "IsBlackOpsJumpConduitPassenger";
    }
}
