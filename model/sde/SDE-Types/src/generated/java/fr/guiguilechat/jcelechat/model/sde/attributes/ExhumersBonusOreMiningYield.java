package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ExhumersBonusOreMiningYield
    extends IntAttribute
{
    public static final ExhumersBonusOreMiningYield INSTANCE = new ExhumersBonusOreMiningYield();

    @Override
    public int getId() {
        return  3197;
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
        return "ExhumersBonusOreMiningYield";
    }
}
