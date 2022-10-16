package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Used to describe what sex a given item is meant for.
 * 
 *  1 = Male,
 *  2 = Unisex,
 *  3 = Female
 */
public class Gender
    extends IntAttribute
{
    public static final Gender INSTANCE = new Gender();

    @Override
    public int getId() {
        return  1773;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  2.0;
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
        return "Gender";
    }
}
