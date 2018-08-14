package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class StrategicCruiserBlueprints
    extends Blueprint
{
    /**
     * This is a bookkeeping attribute for blueprints, which will hopefully be deprecated by the end of 2014
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double IndustryBlueprintRank;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    public final static StrategicCruiserBlueprints.MetaGroup METAGROUP = new StrategicCruiserBlueprints.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1955 :
            {
                return IndustryBlueprintRank;
            }
            case  422 :
            {
                return TechLevel;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<StrategicCruiserBlueprints> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StrategicCruiserBlueprints>
    {
        public final static String RESOURCE_PATH = "SDE/items/blueprint/StrategicCruiserBlueprints.yaml";
        private Map<String, StrategicCruiserBlueprints> cache = (null);

        @Override
        public IMetaCategory<? super StrategicCruiserBlueprints> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  996;
        }

        @Override
        public String getName() {
            return "StrategicCruiserBlueprints";
        }

        @Override
        public synchronized Map<String, StrategicCruiserBlueprints> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(StrategicCruiserBlueprints.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, StrategicCruiserBlueprints> items;
        }
    }
}
