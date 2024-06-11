package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


public class UpwellFreighterCapitalFlexHardenerFittingCapBonus
    extends RealAttribute
{
    public static final UpwellFreighterCapitalFlexHardenerFittingCapBonus INSTANCE = new UpwellFreighterCapitalFlexHardenerFittingCapBonus();

    @Override
    public int getId() {
        return  5655;
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
        return "UpwellFreighterCapitalFlexHardenerFittingCapBonus";
    }
}
