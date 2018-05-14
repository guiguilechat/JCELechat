package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Rate of fire
 */
public class FighterAbilityAttackMissileDuration
    extends IntAttribute
{
    public final static FighterAbilityAttackMissileDuration INSTANCE = new FighterAbilityAttackMissileDuration();

    @Override
    public int getId() {
        return  2233;
    }

    @Override
    public int getCatId() {
        return  34;
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
}
