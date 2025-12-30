package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * sets the weather effect type for abyssal deadspace keys
 */
public class WeatherID
    extends IntAttribute
{
    public static final WeatherID INSTANCE = new WeatherID();

    @Override
    public int getId() {
        return  2760;
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
        return "WeatherID";
    }
}
