package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The maximum number of capital ships that can be jumped per activation
 */
public class MjdCapitalShipJumpCap
    extends IntAttribute
{
    public static final MjdCapitalShipJumpCap INSTANCE = new MjdCapitalShipJumpCap();

    @Override
    public int getId() {
        return  5686;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "MjdCapitalShipJumpCap";
    }
}
