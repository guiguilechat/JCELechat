package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class FighterAbilityECMTargetJam
    extends IntAttribute
{
    public static final FighterAbilityECMTargetJam INSTANCE = new FighterAbilityECMTargetJam();

    @Override
    public int getId() {
        return  2251;
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
        return "FighterAbilityECMTargetJam";
    }
}
