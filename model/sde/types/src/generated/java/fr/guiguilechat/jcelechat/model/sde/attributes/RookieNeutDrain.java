package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Increase in Energy Neutralizer drain amount
 */
public class RookieNeutDrain
    extends IntAttribute
{
    public static final RookieNeutDrain INSTANCE = new RookieNeutDrain();

    @Override
    public int getId() {
        return  1860;
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
        return true;
    }

    @Override
    public String toString() {
        return "RookieNeutDrain";
    }
}
