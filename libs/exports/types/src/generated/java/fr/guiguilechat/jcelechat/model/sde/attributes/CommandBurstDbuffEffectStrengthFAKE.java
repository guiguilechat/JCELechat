package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This doesn't actually do anything... It's just to show the dbuff % increases in the client which currently doesn't display this and we rely on the module descriptions to inform players. 
 */
public class CommandBurstDbuffEffectStrengthFAKE
    extends IntAttribute
{
    public static final CommandBurstDbuffEffectStrengthFAKE INSTANCE = new CommandBurstDbuffEffectStrengthFAKE();

    @Override
    public int getId() {
        return  5949;
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
        return "CommandBurstDbuffEffectStrengthFAKE";
    }
}
