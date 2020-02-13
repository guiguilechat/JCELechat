package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 *  0=None
 *  1=Anti-Fighter
 *  2=General
 *  3=Ewar
 *  4=TorpedoBomber
 *  5=AOEBomber
 */
public class FighterSquadronRole
    extends IntAttribute
{
    public static final FighterSquadronRole INSTANCE = new FighterSquadronRole();

    @Override
    public int getId() {
        return  2270;
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
        return "FighterSquadronRole";
    }
}
