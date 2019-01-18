package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class FighterSquadronIsStandupSupport
    extends IntAttribute
{
    public static final FighterSquadronIsStandupSupport INSTANCE = new FighterSquadronIsStandupSupport();

    @Override
    public int getId() {
        return  2741;
    }

    @Override
    public int getCatId() {
        return  38;
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
        return "FighterSquadronIsStandupSupport";
    }
}
