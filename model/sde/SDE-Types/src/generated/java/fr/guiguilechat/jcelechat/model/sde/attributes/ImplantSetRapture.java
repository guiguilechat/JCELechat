package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Rapture Implant Set Bonus
 */
public class ImplantSetRapture
    extends RealAttribute
{
    public static final ImplantSetRapture INSTANCE = new ImplantSetRapture();

    @Override
    public int getId() {
        return  3107;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ImplantSetRapture";
    }
}
