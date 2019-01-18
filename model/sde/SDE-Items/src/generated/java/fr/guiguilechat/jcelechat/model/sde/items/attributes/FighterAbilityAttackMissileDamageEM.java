package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * EM Damage
 */
public class FighterAbilityAttackMissileDamageEM
    extends IntAttribute
{
    public static final FighterAbilityAttackMissileDamageEM INSTANCE = new FighterAbilityAttackMissileDamageEM();

    @Override
    public int getId() {
        return  2227;
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
        return "FighterAbilityAttackMissileDamageEM";
    }
}
