package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class IntegratedMiningScanner
    extends IntAttribute
{
    public static final IntegratedMiningScanner INSTANCE = new IntegratedMiningScanner();

    @Override
    public int getId() {
        return  5978;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "IntegratedMiningScanner";
    }
}
