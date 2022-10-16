package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This is a display-only attribute for showinfo
 */
public class RequiresSovereigntyDisplayOnly
    extends IntAttribute
{
    public static final RequiresSovereigntyDisplayOnly INSTANCE = new RequiresSovereigntyDisplayOnly();

    @Override
    public int getId() {
        return  1806;
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
        return "RequiresSovereigntyDisplayOnly";
    }
}
