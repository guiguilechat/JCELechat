package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 *  1 = ship can use jump drive
 */
public class CanJump
    extends IntAttribute
{
    public static final CanJump INSTANCE = new CanJump();

    @Override
    public int getId() {
        return  861;
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
        return "CanJump";
    }
}
