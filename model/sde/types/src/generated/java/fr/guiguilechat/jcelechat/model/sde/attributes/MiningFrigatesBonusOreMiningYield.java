package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * ORE Mining frigate bonus 1
 */
public class MiningFrigatesBonusOreMiningYield
    extends IntAttribute
{
    public static final MiningFrigatesBonusOreMiningYield INSTANCE = new MiningFrigatesBonusOreMiningYield();

    @Override
    public int getId() {
        return  1842;
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
        return "MiningFrigatesBonusOreMiningYield";
    }
}
