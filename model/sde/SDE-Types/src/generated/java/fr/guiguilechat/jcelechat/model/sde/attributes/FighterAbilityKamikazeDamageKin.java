package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class FighterAbilityKamikazeDamageKin
    extends IntAttribute
{
    public static final FighterAbilityKamikazeDamageKin INSTANCE = new FighterAbilityKamikazeDamageKin();

    @Override
    public int getId() {
        return  2327;
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
        return "FighterAbilityKamikazeDamageKin";
    }
}
