package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Shield HP Bonus used for nirvana implants
 */
public class ShieldHpBonus
    extends IntAttribute
{
    public static final ShieldHpBonus INSTANCE = new ShieldHpBonus();

    @Override
    public int getId() {
        return  3015;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
        return "ShieldHpBonus";
    }
}
