package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * a bonus to standing towards npc with negativa security status
 */
public class CriminalConnectionsBonus
    extends IntAttribute
{
    public final static CriminalConnectionsBonus INSTANCE = new CriminalConnectionsBonus();

    @Override
    public int getId() {
        return  361;
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

    @Override
    public String toString() {
        return "CriminalConnectionsBonus";
    }
}
