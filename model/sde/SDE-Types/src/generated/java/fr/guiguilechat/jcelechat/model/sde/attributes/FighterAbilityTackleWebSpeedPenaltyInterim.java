package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class FighterAbilityTackleWebSpeedPenaltyInterim
    extends IntAttribute
{
    public static final FighterAbilityTackleWebSpeedPenaltyInterim INSTANCE = new FighterAbilityTackleWebSpeedPenaltyInterim();

    @Override
    public int getId() {
        return  2243;
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
        return "FighterAbilityTackleWebSpeedPenaltyInterim";
    }
}
