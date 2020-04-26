package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class FighterAbilityMissilesResistanceID
    extends IntAttribute
{
    public static final FighterAbilityMissilesResistanceID INSTANCE = new FighterAbilityMissilesResistanceID();

    @Override
    public int getId() {
        return  2170;
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
        return true;
    }

    @Override
    public String toString() {
        return "FighterAbilityMissilesResistanceID";
    }
}
