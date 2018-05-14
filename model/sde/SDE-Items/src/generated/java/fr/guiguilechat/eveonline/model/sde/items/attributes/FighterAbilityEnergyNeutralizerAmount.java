package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Energy Amount Neutralized
 */
public class FighterAbilityEnergyNeutralizerAmount
    extends IntAttribute
{
    public final static FighterAbilityEnergyNeutralizerAmount INSTANCE = new FighterAbilityEnergyNeutralizerAmount();

    @Override
    public int getId() {
        return  2211;
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
