package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class DecryptorsSleepers
    extends Commodity
{
    /**
     * Modifies the mineral efficiency of invented BPCs
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double InventionMEModifier;
    /**
     * Modifies the max runs in a blueprint created through invention
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double InventionMaxRunModifier;
    /**
     * Modifies base chance of successful invention
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double InventionPropabilityMultiplier;
    /**
     * Modifies the time efficiency of invented BPCs
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double InventionTEModifier;
    public static final DecryptorsSleepers.MetaGroup METAGROUP = new DecryptorsSleepers.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1113 :
            {
                return InventionMEModifier;
            }
            case  1124 :
            {
                return InventionMaxRunModifier;
            }
            case  1112 :
            {
                return InventionPropabilityMultiplier;
            }
            case  1114 :
            {
                return InventionTEModifier;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<DecryptorsSleepers> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DecryptorsSleepers>
    {
        public static final String RESOURCE_PATH = "SDE/items/commodity/DecryptorsSleepers.yaml";
        private Map<String, DecryptorsSleepers> cache = (null);

        @Override
        public IMetaCategory<? super DecryptorsSleepers> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  732;
        }

        @Override
        public String getName() {
            return "DecryptorsSleepers";
        }

        @Override
        public synchronized Map<String, DecryptorsSleepers> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DecryptorsSleepers.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DecryptorsSleepers> items;
        }
    }
}
