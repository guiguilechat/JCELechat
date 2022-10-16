package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Range of the automated targeting systems hostile targeting area.
 */
public class TargetHostileRange
    extends IntAttribute
{
    public static final TargetHostileRange INSTANCE = new TargetHostileRange();

    @Override
    public int getId() {
        return  143;
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
        return "TargetHostileRange";
    }
}
