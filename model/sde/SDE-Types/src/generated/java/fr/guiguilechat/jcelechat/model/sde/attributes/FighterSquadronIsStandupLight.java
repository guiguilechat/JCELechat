package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class FighterSquadronIsStandupLight
    extends IntAttribute
{
    public static final FighterSquadronIsStandupLight INSTANCE = new FighterSquadronIsStandupLight();

    @Override
    public int getId() {
        return  2740;
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
        return "FighterSquadronIsStandupLight";
    }
}
