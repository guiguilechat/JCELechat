package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class EntosisDurationMultiplier
    extends IntAttribute
{
    public static final EntosisDurationMultiplier INSTANCE = new EntosisDurationMultiplier();

    @Override
    public int getId() {
        return  2021;
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
        return true;
    }

    @Override
    public String toString() {
        return "EntosisDurationMultiplier";
    }
}
