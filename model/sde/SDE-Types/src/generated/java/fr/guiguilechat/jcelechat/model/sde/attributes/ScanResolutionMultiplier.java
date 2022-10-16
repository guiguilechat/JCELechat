package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Improves the targeting time of ships by boosting the Scan Resolution.
 */
public class ScanResolutionMultiplier
    extends RealAttribute
{
    public static final ScanResolutionMultiplier INSTANCE = new ScanResolutionMultiplier();

    @Override
    public int getId() {
        return  565;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
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
        return "ScanResolutionMultiplier";
    }
}
