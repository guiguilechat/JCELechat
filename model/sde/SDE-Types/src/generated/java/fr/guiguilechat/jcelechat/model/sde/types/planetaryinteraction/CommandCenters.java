package fr.guiguilechat.jcelechat.model.sde.types.planetaryinteraction;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExportTax;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.PlanetRestriction;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.types.PlanetaryInteraction;
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
    public int cpuoutput;
    /**
     * Base export tax (ISK per m3 of volume) on commodities exported from a planet via this pin.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int exporttax;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevelold;
    /**
     * This type can only be found/used/created on a planet matching this type ID.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int planetrestriction;
    /**
     * power output of power core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int poweroutput;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1level;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {PlanetRestriction.INSTANCE, CpuOutput.INSTANCE, Radius.INSTANCE, Mass.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill1 .INSTANCE, Capacity.INSTANCE, ExportTax.INSTANCE, MetaLevelOld.INSTANCE, PowerOutput.INSTANCE })));
    public static final CommandCenters.MetaGroup METAGROUP = new CommandCenters.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  48 :
            {
                return cpuoutput;
            }
            case  1639 :
            {
                return exporttax;
            }
            case  633 :
            {
                return metalevelold;
            }
            case  1632 :
            {
                return planetrestriction;
            }
            case  11 :
            {
                return poweroutput;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<CommandCenters> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CommandCenters>
    {
        public static final String RESOURCE_PATH = "SDE/types/planetaryinteraction/CommandCenters.yaml";
        private Map<String, CommandCenters> cache = (null);

        @Override
        public IMetaCategory<? super CommandCenters> category() {
            return PlanetaryInteraction.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1027;
        }

        @Override
        public String getName() {
            return "CommandCenters";
        }

        @Override
        public synchronized Map<String, CommandCenters> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CommandCenters.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, CommandCenters> types;
        }
    }
}
