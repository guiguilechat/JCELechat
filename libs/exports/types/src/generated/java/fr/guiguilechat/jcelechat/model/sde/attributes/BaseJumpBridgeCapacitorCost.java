package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Base capacitor cost to jump this ship through a Structure Jump Bridge like the Ansiblex.
 */
public class BaseJumpBridgeCapacitorCost
    extends IntAttribute
{
    public static final BaseJumpBridgeCapacitorCost INSTANCE = new BaseJumpBridgeCapacitorCost();

    @Override
    public int getId() {
        return  6364;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "BaseJumpBridgeCapacitorCost";
    }
}
