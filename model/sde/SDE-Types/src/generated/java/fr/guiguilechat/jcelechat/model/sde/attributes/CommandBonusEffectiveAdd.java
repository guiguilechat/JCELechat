package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * commandBonusEffectiveAdd
 */
public class CommandBonusEffectiveAdd
    extends IntAttribute
{
    public static final CommandBonusEffectiveAdd INSTANCE = new CommandBonusEffectiveAdd();

    @Override
    public int getId() {
        return  1238;
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
        return "CommandBonusEffectiveAdd";
    }
}
