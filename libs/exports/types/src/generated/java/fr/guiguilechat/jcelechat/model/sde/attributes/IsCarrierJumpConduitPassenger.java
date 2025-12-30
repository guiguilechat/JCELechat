package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class IsCarrierJumpConduitPassenger
    extends IntAttribute
{
    public static final IsCarrierJumpConduitPassenger INSTANCE = new IsCarrierJumpConduitPassenger();

    @Override
    public int getId() {
        return  5682;
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
        return "IsCarrierJumpConduitPassenger";
    }
}
