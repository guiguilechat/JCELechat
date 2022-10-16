package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * a bonus to standing towards npc with negativa security status
 */
public class CriminalConnectionsBonus
    extends IntAttribute
{
    public static final CriminalConnectionsBonus INSTANCE = new CriminalConnectionsBonus();

    @Override
    public int getId() {
        return  361;
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
