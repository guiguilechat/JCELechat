package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * If set to true, this results in no mining waste.
 */
public class IgnoreMiningWaste
    extends IntAttribute
{
    public static final IgnoreMiningWaste INSTANCE = new IgnoreMiningWaste();

    @Override
    public int getId() {
        return  3236;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return false;
    }

    @Override
    public String toString() {
        return "IgnoreMiningWaste";
    }
}
