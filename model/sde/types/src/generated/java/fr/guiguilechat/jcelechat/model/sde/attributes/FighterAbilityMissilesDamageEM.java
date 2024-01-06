package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * EM Damage
 */
public class FighterAbilityMissilesDamageEM
    extends IntAttribute
{
    public static final FighterAbilityMissilesDamageEM INSTANCE = new FighterAbilityMissilesDamageEM();

    @Override
    public int getId() {
        return  2131;
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
        return "FighterAbilityMissilesDamageEM";
    }
}
