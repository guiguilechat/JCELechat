package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class CapitalMJDCapReductionBonus
    extends IntAttribute
{
    public static final CapitalMJDCapReductionBonus INSTANCE = new CapitalMJDCapReductionBonus();

    @Override
    public int getId() {
        return  5683;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "CapitalMJDCapReductionBonus";
    }
}
