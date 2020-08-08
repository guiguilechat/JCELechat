package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ActiveSystemJump
    extends IntAttribute
{
    public static final ActiveSystemJump INSTANCE = new ActiveSystemJump();

    @Override
    public int getId() {
        return  3025;
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
        return "ActiveSystemJump";
    }
}
