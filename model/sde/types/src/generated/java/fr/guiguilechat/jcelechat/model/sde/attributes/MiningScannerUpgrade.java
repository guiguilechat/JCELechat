package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class MiningScannerUpgrade
    extends IntAttribute
{
    public static final MiningScannerUpgrade INSTANCE = new MiningScannerUpgrade();

    @Override
    public int getId() {
        return  5979;
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
        return "MiningScannerUpgrade";
    }
}
