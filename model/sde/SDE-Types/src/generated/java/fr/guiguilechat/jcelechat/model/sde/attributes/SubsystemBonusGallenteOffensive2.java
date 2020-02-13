package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class SubsystemBonusGallenteOffensive2
    extends IntAttribute
{
    public static final SubsystemBonusGallenteOffensive2 INSTANCE = new SubsystemBonusGallenteOffensive2();

    @Override
    public int getId() {
        return  1521;
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
        return "SubsystemBonusGallenteOffensive2";
    }
}
