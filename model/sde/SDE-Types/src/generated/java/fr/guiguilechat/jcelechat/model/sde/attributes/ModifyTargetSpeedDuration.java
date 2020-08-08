package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Duration of entities Stasis Web 
 */
public class ModifyTargetSpeedDuration
    extends DoubleAttribute
{
    public static final ModifyTargetSpeedDuration INSTANCE = new ModifyTargetSpeedDuration();

    @Override
    public int getId() {
        return  513;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  5000.0;
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
        return "ModifyTargetSpeedDuration";
    }
}
