package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * For use with the Networked Sensor Array dogma effect, moduleBonusNetworkedSensorArray [6567]. If this value is set to 1, prevent activation of a Capital Micro Jump Drive or Capital Micro Jump Field Generator.
 */
public class NetworkedSensorArrayDisallowCapitalMicroJump
    extends IntAttribute
{
    public static final NetworkedSensorArrayDisallowCapitalMicroJump INSTANCE = new NetworkedSensorArrayDisallowCapitalMicroJump();

    @Override
    public int getId() {
        return  5700;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return false;
    }

    @Override
    public String toString() {
        return "NetworkedSensorArrayDisallowCapitalMicroJump";
    }
}
