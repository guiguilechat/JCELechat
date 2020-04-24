package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class PassiveExplosiveDamageResonanceMultiplier
    extends IntAttribute
{
    public static final PassiveExplosiveDamageResonanceMultiplier INSTANCE = new PassiveExplosiveDamageResonanceMultiplier();

    @Override
    public int getId() {
        return  967;
    }

    @Override
    public int getCatId() {
        return  9;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "PassiveExplosiveDamageResonanceMultiplier";
    }
}
