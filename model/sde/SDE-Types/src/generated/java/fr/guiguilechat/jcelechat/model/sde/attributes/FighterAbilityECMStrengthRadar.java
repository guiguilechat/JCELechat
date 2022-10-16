package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Radar ECM Jammer Strength
 */
public class FighterAbilityECMStrengthRadar
    extends RealAttribute
{
    public static final FighterAbilityECMStrengthRadar INSTANCE = new FighterAbilityECMStrengthRadar();

    @Override
    public int getId() {
        return  2249;
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
        return false;
    }

    @Override
    public String toString() {
        return "FighterAbilityECMStrengthRadar";
    }
}
