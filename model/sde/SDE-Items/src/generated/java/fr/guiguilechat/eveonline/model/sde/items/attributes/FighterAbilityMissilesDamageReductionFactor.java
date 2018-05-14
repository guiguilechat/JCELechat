package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class FighterAbilityMissilesDamageReductionFactor
    extends DoubleAttribute
{
    public final static FighterAbilityMissilesDamageReductionFactor INSTANCE = new FighterAbilityMissilesDamageReductionFactor();

    @Override
    public int getId() {
        return  2127;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return false;
    }
}
