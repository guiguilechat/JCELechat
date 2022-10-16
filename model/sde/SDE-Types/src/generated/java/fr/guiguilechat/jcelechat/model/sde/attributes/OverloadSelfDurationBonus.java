package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class OverloadSelfDurationBonus
    extends RealAttribute
{
    public static final OverloadSelfDurationBonus INSTANCE = new OverloadSelfDurationBonus();

    @Override
    public int getId() {
        return  1206;
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
        return "OverloadSelfDurationBonus";
    }
}
