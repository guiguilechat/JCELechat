package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class DestroyerROFpenality
    extends DoubleAttribute
{
    public static final DestroyerROFpenality INSTANCE = new DestroyerROFpenality();

    @Override
    public int getId() {
        return  727;
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
        return "DestroyerROFpenality";
    }
}
