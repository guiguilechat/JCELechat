package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Signature Resolution of Energy Neutralizer
 */
public class EnergyNeutralizerSignatureResolution
    extends IntAttribute
{
    public final static EnergyNeutralizerSignatureResolution INSTANCE = new EnergyNeutralizerSignatureResolution();

    @Override
    public int getId() {
        return  2451;
    }

    @Override
    public int getCatId() {
        return  22;
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
        return "EnergyNeutralizerSignatureResolution";
    }
}