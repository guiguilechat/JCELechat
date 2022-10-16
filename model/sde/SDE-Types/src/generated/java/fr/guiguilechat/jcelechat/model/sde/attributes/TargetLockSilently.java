package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * A attribute that prevents the ship or entity from flashing yellow when he has the player locked and is applying effects on the player.
 */
public class TargetLockSilently
    extends IntAttribute
{
    public static final TargetLockSilently INSTANCE = new TargetLockSilently();

    @Override
    public int getId() {
        return  3176;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "TargetLockSilently";
    }
}
