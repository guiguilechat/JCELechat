package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


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
    public final static Gender INSTANCE = new Gender();

    @Override
    public int getId() {
        return  1773;
    }

    @Override
    public int getCatId() {
        return  7;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
