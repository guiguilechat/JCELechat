package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Kinetic Damage
 */
public class FighterAbilityAttackMissileDamageKin
    extends IntAttribute
{
    public static final FighterAbilityAttackMissileDamageKin INSTANCE = new FighterAbilityAttackMissileDamageKin();

    @Override
    public int getId() {
        return  2229;
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
        return "FighterAbilityAttackMissileDamageKin";
    }
}
