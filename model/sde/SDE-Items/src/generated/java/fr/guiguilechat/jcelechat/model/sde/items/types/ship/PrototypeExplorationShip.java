package fr.guiguilechat.jcelechat.model.sde.items.types.ship;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Ship;
import org.yaml.snakeyaml.Yaml;

public class PrototypeExplorationShip
    extends Ship
{
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MainColor;
    /**
     * Specifies the maximum numbers of passengers that the ship can have
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxPassengers;
    /**
     * scanning speed in milliseconds
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ScanSpeed;
    public final static String RESOURCE_PATH = "SDE/items/ship/PrototypeExplorationShip.yaml";
    private static LinkedHashMap<String, PrototypeExplorationShip> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  124 :
            {
                return MainColor;
            }
            case  129 :
            {
                return MaxPassengers;
            }
            case  79 :
            {
                return ScanSpeed;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1022;
    }

    @Override
    public Class<?> getGroup() {
        return PrototypeExplorationShip.class;
    }

    public static synchronized LinkedHashMap<String, PrototypeExplorationShip> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(PrototypeExplorationShip.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, PrototypeExplorationShip> items;
    }
}
