package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Time Bonus for refinery rigs
 */
public class RefRigTimeBonus
    extends IntAttribute
{
    public static final RefRigTimeBonus INSTANCE = new RefRigTimeBonus();

    @Override
    public int getId() {
        return  2713;
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
        return "RefRigTimeBonus";
    }
}
