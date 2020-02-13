package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Improves the targeting time of ships by boosting the Scan Resolution.
 */
public class ScanResolutionMultiplier
    extends DoubleAttribute
{
    public static final ScanResolutionMultiplier INSTANCE = new ScanResolutionMultiplier();

    @Override
    public int getId() {
        return  565;
    }

    @Override
    public int getCatId() {
        return  6;
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
        return "ScanResolutionMultiplier";
    }
}
