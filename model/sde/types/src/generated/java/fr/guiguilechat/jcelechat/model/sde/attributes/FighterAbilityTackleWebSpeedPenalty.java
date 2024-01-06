package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Maximum Velocity Bonus
 */
public class FighterAbilityTackleWebSpeedPenalty
    extends IntAttribute
{
    public static final FighterAbilityTackleWebSpeedPenalty INSTANCE = new FighterAbilityTackleWebSpeedPenalty();

    @Override
    public int getId() {
        return  2242;
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
        return "FighterAbilityTackleWebSpeedPenalty";
    }
}
