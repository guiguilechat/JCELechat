package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Autogenerated skill attribute, fastTalkMutator
 */
public class FastTalkMutator
    extends IntAttribute
{
    public static final FastTalkMutator INSTANCE = new FastTalkMutator();

    @Override
    public int getId() {
        return  415;
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
        return "FastTalkMutator";
    }
}
