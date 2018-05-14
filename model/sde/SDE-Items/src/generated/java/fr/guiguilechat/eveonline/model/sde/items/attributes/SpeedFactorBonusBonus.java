package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Modification of Afterburner and Microwarpdrive Max Velocity Bonus
 */
public class SpeedFactorBonusBonus
    extends IntAttribute
{
    public final static SpeedFactorBonusBonus INSTANCE = new SpeedFactorBonusBonus();

    @Override
    public int getId() {
        return  1326;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }
}
