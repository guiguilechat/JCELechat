package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Restrict activation to this one module group.
 */
public class TargetGroup
    extends IntAttribute
{
    public static final TargetGroup INSTANCE = new TargetGroup();

    @Override
    public int getId() {
        return  189;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "TargetGroup";
    }
}
