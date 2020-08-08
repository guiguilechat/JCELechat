package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Amount of stolen materials that is destroyed.
 */
public class SiphonWasteAmount
    extends IntAttribute
{
    public static final SiphonWasteAmount INSTANCE = new SiphonWasteAmount();

    @Override
    public int getId() {
        return  1930;
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
        return "SiphonWasteAmount";
    }
}
