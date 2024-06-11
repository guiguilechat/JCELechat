package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Maximum Velocity Bonus
 */
public class FighterAbilityStasisWebifierSpeedPenalty
    extends RealAttribute
{
    public static final FighterAbilityStasisWebifierSpeedPenalty INSTANCE = new FighterAbilityStasisWebifierSpeedPenalty();

    @Override
    public int getId() {
        return  2184;
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
        return "FighterAbilityStasisWebifierSpeedPenalty";
    }
}
