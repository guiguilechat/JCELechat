package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Alliance Tournament XX Frigate Bonus
 */
public class ATFrigDmgBonus
    extends RealAttribute
{
    public static final ATFrigDmgBonus INSTANCE = new ATFrigDmgBonus();

    @Override
    public int getId() {
        return  5727;
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
        return "ATFrigDmgBonus";
    }
}
