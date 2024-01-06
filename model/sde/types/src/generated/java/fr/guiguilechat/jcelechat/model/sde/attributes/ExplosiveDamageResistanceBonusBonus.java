package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ExplosiveDamageResistanceBonusBonus
    extends IntAttribute
{
    public static final ExplosiveDamageResistanceBonusBonus INSTANCE = new ExplosiveDamageResistanceBonusBonus();

    @Override
    public int getId() {
        return  2403;
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
        return true;
    }

    @Override
    public String toString() {
        return "ExplosiveDamageResistanceBonusBonus";
    }
}
