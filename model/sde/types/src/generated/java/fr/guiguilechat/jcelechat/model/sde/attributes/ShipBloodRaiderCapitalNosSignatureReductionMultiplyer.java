package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


public class ShipBloodRaiderCapitalNosSignatureReductionMultiplyer
    extends RealAttribute
{
    public static final ShipBloodRaiderCapitalNosSignatureReductionMultiplyer INSTANCE = new ShipBloodRaiderCapitalNosSignatureReductionMultiplyer();

    @Override
    public int getId() {
        return  5787;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "ShipBloodRaiderCapitalNosSignatureReductionMultiplyer";
    }
}
