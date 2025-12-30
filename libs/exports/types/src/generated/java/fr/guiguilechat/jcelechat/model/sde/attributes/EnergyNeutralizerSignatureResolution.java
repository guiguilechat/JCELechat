package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Signature Resolution of Energy Neutralizer
 */
public class EnergyNeutralizerSignatureResolution
    extends IntAttribute
{
    public static final EnergyNeutralizerSignatureResolution INSTANCE = new EnergyNeutralizerSignatureResolution();

    @Override
    public int getId() {
        return  2451;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
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
