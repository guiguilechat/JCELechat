package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Module type ID to pre-fit into service slot 0
 */
public class PreFitServiceSlot0
    extends IntAttribute
{
    public static final PreFitServiceSlot0 INSTANCE = new PreFitServiceSlot0();

    @Override
    public int getId() {
        return  2792;
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
        return "PreFitServiceSlot0";
    }
}
