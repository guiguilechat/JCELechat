package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Increase in Statis Webifier speed reduction
 */
public class RookieWebAmount
    extends IntAttribute
{
    public static final RookieWebAmount INSTANCE = new RookieWebAmount();

    @Override
    public int getId() {
        return  1861;
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
        return "RookieWebAmount";
    }
}
