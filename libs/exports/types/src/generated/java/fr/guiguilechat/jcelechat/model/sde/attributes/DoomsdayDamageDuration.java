package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class DoomsdayDamageDuration
    extends IntAttribute
{
    public static final DoomsdayDamageDuration INSTANCE = new DoomsdayDamageDuration();

    @Override
    public int getId() {
        return  2264;
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
        return "DoomsdayDamageDuration";
    }
}
