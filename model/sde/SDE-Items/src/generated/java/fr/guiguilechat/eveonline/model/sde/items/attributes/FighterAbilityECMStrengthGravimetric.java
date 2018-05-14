package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Gravimetric ECM Jammer Strength
 */
public class FighterAbilityECMStrengthGravimetric
    extends DoubleAttribute
{
    public final static FighterAbilityECMStrengthGravimetric INSTANCE = new FighterAbilityECMStrengthGravimetric();

    @Override
    public int getId() {
        return  2246;
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
