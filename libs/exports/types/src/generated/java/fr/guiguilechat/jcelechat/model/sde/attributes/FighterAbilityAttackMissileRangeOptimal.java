package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Optimal Range
 */
public class FighterAbilityAttackMissileRangeOptimal
    extends IntAttribute
{
    public static final FighterAbilityAttackMissileRangeOptimal INSTANCE = new FighterAbilityAttackMissileRangeOptimal();

    @Override
    public int getId() {
        return  2236;
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
        return "FighterAbilityAttackMissileRangeOptimal";
    }
}
