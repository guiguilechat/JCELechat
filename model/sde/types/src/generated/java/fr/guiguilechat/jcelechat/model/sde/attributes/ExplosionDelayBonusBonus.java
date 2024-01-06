package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ExplosionDelayBonusBonus
    extends IntAttribute
{
    public static final ExplosionDelayBonusBonus INSTANCE = new ExplosionDelayBonusBonus();

    @Override
    public int getId() {
        return  2026;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1399.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ExplosionDelayBonusBonus";
    }
}
