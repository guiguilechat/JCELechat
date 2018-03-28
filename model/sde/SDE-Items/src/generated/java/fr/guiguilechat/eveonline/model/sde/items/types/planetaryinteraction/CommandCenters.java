package fr.guiguilechat.eveonline.model.sde.items.types.planetaryinteraction;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.PlanetaryInteraction;
import org.yaml.snakeyaml.Yaml;

public class CommandCenters
    extends PlanetaryInteraction
{
    /**
     * CPU output of ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CpuOutput;
    /**
     * Base export tax (ISK per m3 of volume) on commodities exported from a planet via this pin.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ExportTax;
    /**
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaLevel;
    /**
     * This type can only be found/used/created on a planet matching this type ID.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PlanetRestriction;
    /**
     * power output of power core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PowerOutput;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    public final static String RESOURCE_PATH = "SDE/items/planetaryinteraction/CommandCenters.yaml";
    private static LinkedHashMap<String, CommandCenters> cache = (null);

    @Override
    public int getGroupId() {
        return  1027;
    }

    @Override
    public Class<?> getGroup() {
        return CommandCenters.class;
    }

    public static synchronized LinkedHashMap<String, CommandCenters> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(CommandCenters.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, CommandCenters> items;
    }
}
