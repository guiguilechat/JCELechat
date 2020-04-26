package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class FighterAbilityECMTargetSuccess
    extends IntAttribute
{
    public static final FighterAbilityECMTargetSuccess INSTANCE = new FighterAbilityECMTargetSuccess();

    @Override
    public int getId() {
        return  2250;
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
        return "FighterAbilityECMTargetSuccess";
    }
}
