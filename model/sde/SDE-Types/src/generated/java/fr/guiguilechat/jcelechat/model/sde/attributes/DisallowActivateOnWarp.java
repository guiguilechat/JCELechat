package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Stops the module from being activated if the ship is aligning to warp.
 */
public class DisallowActivateOnWarp
    extends IntAttribute
{
    public static final DisallowActivateOnWarp INSTANCE = new DisallowActivateOnWarp();

    @Override
    public int getId() {
        return  1245;
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
        return "DisallowActivateOnWarp";
    }
}
