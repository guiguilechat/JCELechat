package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class FighterAbilityTackleWebSpeedPenaltyInterim
    extends IntAttribute
{
    public final static FighterAbilityTackleWebSpeedPenaltyInterim INSTANCE = new FighterAbilityTackleWebSpeedPenaltyInterim();

    @Override
    public int getId() {
        return  2243;
    }

    @Override
    public int getCatId() {
        return  34;
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
        return "FighterAbilityTackleWebSpeedPenaltyInterim";
    }
}
