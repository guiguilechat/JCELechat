package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Range of entities Stasis Web
 */
public class ModifyTargetSpeedRange
    extends IntAttribute
{
    public static final ModifyTargetSpeedRange INSTANCE = new ModifyTargetSpeedRange();

    @Override
    public int getId() {
        return  514;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  20000.0;
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
        return "ModifyTargetSpeedRange";
    }
}
