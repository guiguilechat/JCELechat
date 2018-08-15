package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class FighterAbilityStasisWebifierSpeedPenaltyInterim
    extends IntAttribute
{
    public final static FighterAbilityStasisWebifierSpeedPenaltyInterim INSTANCE = new FighterAbilityStasisWebifierSpeedPenaltyInterim();

    @Override
    public int getId() {
        return  2185;
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
        return "FighterAbilityStasisWebifierSpeedPenaltyInterim";
    }
}