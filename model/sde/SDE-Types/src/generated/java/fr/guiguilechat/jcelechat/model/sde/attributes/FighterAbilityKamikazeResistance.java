package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class FighterAbilityKamikazeResistance
    extends IntAttribute
{
    public static final FighterAbilityKamikazeResistance INSTANCE = new FighterAbilityKamikazeResistance();

    @Override
    public int getId() {
        return  2433;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "FighterAbilityKamikazeResistance";
    }
}
