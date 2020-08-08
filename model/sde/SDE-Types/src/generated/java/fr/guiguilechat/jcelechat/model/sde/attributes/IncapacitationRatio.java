package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * The hull damage proportion at which an entity becomes incapacitated.
 */
public class IncapacitationRatio
    extends DoubleAttribute
{
    public static final IncapacitationRatio INSTANCE = new IncapacitationRatio();

    @Override
    public int getId() {
        return  156;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "IncapacitationRatio";
    }
}
