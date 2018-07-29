package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class ModeDamageBonusPostDiv
    extends DoubleAttribute
{
    public final static ModeDamageBonusPostDiv INSTANCE = new ModeDamageBonusPostDiv();

    @Override
    public int getId() {
        return  2589;
    }

    @Override
    public int getCatId() {
        return  29;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
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
        return "ModeDamageBonusPostDiv";
    }
}
