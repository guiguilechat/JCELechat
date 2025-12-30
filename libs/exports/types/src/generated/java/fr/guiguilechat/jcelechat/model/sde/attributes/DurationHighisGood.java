package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Length of activation time.
 */
public class DurationHighisGood
    extends IntAttribute
{
    public static final DurationHighisGood INSTANCE = new DurationHighisGood();

    @Override
    public int getId() {
        return  3115;
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
        return "DurationHighisGood";
    }
}
