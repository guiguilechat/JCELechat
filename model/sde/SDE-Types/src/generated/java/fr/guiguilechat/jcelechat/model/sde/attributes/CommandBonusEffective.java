package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * commandBonusEffective
 */
public class CommandBonusEffective
    extends IntAttribute
{
    public static final CommandBonusEffective INSTANCE = new CommandBonusEffective();

    @Override
    public int getId() {
        return  1236;
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
        return "CommandBonusEffective";
    }
}
