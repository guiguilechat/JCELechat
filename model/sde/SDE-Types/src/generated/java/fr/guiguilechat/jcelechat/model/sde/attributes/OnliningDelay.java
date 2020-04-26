package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * How long it takes to bring this object online.
 */
public class OnliningDelay
    extends IntAttribute
{
    public static final OnliningDelay INSTANCE = new OnliningDelay();

    @Override
    public int getId() {
        return  677;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  60000.0;
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
        return "OnliningDelay";
    }
}
