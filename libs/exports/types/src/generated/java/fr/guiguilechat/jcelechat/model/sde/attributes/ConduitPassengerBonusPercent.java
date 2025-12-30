package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ConduitPassengerBonusPercent
    extends IntAttribute
{
    public static final ConduitPassengerBonusPercent INSTANCE = new ConduitPassengerBonusPercent();

    @Override
    public int getId() {
        return  5681;
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
        return "ConduitPassengerBonusPercent";
    }
}
