package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * On a targeted module, module can only be activated against a target from this type list.
 */
public class TargetFilterTypelistID
    extends IntAttribute
{
    public static final TargetFilterTypelistID INSTANCE = new TargetFilterTypelistID();

    @Override
    public int getId() {
        return  189;
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
        return "TargetFilterTypelistID";
    }
}
