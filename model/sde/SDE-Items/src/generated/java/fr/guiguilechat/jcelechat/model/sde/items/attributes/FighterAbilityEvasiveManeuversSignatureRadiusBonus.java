package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class FighterAbilityEvasiveManeuversSignatureRadiusBonus
    extends IntAttribute
{
    public final static FighterAbilityEvasiveManeuversSignatureRadiusBonus INSTANCE = new FighterAbilityEvasiveManeuversSignatureRadiusBonus();

    @Override
    public int getId() {
        return  2225;
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
        return "FighterAbilityEvasiveManeuversSignatureRadiusBonus";
    }
}