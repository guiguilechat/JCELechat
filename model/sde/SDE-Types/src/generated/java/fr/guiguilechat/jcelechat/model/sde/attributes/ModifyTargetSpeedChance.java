package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Chance that  an entity will use a Stasis Web on a target.
 */
public class ModifyTargetSpeedChance
    extends RealAttribute
{
    public static final ModifyTargetSpeedChance INSTANCE = new ModifyTargetSpeedChance();

    @Override
    public int getId() {
        return  512;
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
        return "ModifyTargetSpeedChance";
    }
}
