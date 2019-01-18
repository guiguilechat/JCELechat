package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * speed bonus when researching blueprint mineral need
 */
public class MineralNeedResearchSpeed
    extends IntAttribute
{
    public static final MineralNeedResearchSpeed INSTANCE = new MineralNeedResearchSpeed();

    @Override
    public int getId() {
        return  398;
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
        return  1.0;
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
        return "MineralNeedResearchSpeed";
    }
}
