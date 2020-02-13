package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Rig Bonus that affects spew asteroid belt radius from moon extraction
 */
public class MoonRigSpewRadiusBonus
    extends IntAttribute
{
    public static final MoonRigSpewRadiusBonus INSTANCE = new MoonRigSpewRadiusBonus();

    @Override
    public int getId() {
        return  2709;
    }

    @Override
    public int getCatId() {
        return  37;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "MoonRigSpewRadiusBonus";
    }
}
