package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class FighterAbilityKamikazeDamageExp
    extends IntAttribute
{
    public static final FighterAbilityKamikazeDamageExp INSTANCE = new FighterAbilityKamikazeDamageExp();

    @Override
    public int getId() {
        return  2328;
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
        return "FighterAbilityKamikazeDamageExp";
    }
}
