package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class SubsystemMHTFittingReduction
    extends IntAttribute
{
    public static final SubsystemMHTFittingReduction INSTANCE = new SubsystemMHTFittingReduction();

    @Override
    public int getId() {
        return  2666;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public double getDefaultValue() {
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
        return "SubsystemMHTFittingReduction";
    }
}
