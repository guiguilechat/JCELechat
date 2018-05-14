package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Accuracy Falloff
 */
public class FighterAbilityAttackMissileRangeFalloff
    extends IntAttribute
{
    public final static FighterAbilityAttackMissileRangeFalloff INSTANCE = new FighterAbilityAttackMissileRangeFalloff();

    @Override
    public int getId() {
        return  2237;
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
}
