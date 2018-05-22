package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * EM Damage
 */
public class FighterAbilityMissilesDamageEM
    extends IntAttribute
{
    public final static FighterAbilityMissilesDamageEM INSTANCE = new FighterAbilityMissilesDamageEM();

    @Override
    public int getId() {
        return  2131;
    }

    @Override
    public int getCatId() {
        return  34;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
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
        return "FighterAbilityMissilesDamageEM";
    }
}
