package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The amount of time that is taken to refine the ore into the end product.  The structure is busy for the length of this process.
 */
public class OperationalDuration
    extends IntAttribute
{
    public static final OperationalDuration INSTANCE = new OperationalDuration();

    @Override
    public int getId() {
        return  719;
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
        return "OperationalDuration";
    }
}
