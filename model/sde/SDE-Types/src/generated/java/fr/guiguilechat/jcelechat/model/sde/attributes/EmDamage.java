package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * EM damage done.
 */
public class EmDamage
    extends DoubleAttribute
{
    public static final EmDamage INSTANCE = new EmDamage();

    @Override
    public int getId() {
        return  114;
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
        return "EmDamage";
    }
}
