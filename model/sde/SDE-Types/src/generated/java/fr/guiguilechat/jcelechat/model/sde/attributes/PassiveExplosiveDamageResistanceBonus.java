package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class PassiveExplosiveDamageResistanceBonus
    extends IntAttribute
{
    public static final PassiveExplosiveDamageResistanceBonus INSTANCE = new PassiveExplosiveDamageResistanceBonus();

    @Override
    public int getId() {
        return  995;
    }

    @Override
    public int getCatId() {
        return  7;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public double getDefaultValue() {
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
        return "PassiveExplosiveDamageResistanceBonus";
    }
}
