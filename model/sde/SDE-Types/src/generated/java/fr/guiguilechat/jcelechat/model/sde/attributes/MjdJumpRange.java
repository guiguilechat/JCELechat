package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * distance jumped on mjd activation
 */
public class MjdJumpRange
    extends IntAttribute
{
    public static final MjdJumpRange INSTANCE = new MjdJumpRange();

    @Override
    public int getId() {
        return  2066;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
        return "MjdJumpRange";
    }
}
