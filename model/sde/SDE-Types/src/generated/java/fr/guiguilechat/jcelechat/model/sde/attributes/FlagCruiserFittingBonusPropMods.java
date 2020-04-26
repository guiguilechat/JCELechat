package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class FlagCruiserFittingBonusPropMods
    extends IntAttribute
{
    public static final FlagCruiserFittingBonusPropMods INSTANCE = new FlagCruiserFittingBonusPropMods();

    @Override
    public int getId() {
        return  2753;
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
        return true;
    }

    @Override
    public String toString() {
        return "FlagCruiserFittingBonusPropMods";
    }
}
