package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

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
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class ContainerBlueprints
    extends Blueprint
{
    /**
     * This is a bookkeeping attribute for blueprints, which will hopefully be deprecated by the end of 2014
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double IndustryBlueprintRank;
    public final static ContainerBlueprints.MetaGroup METAGROUP = new ContainerBlueprints.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1955 :
            {
                return IndustryBlueprintRank;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<ContainerBlueprints> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ContainerBlueprints>
    {
        public final static String RESOURCE_PATH = "SDE/items/blueprint/ContainerBlueprints.yaml";
        private Map<String, ContainerBlueprints> cache = (null);

        @Override
        public IMetaCategory<? super ContainerBlueprints> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1162;
        }

        @Override
        public String getName() {
            return "ContainerBlueprints";
        }

        @Override
        public synchronized Map<String, ContainerBlueprints> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(ContainerBlueprints.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, ContainerBlueprints> items;
        }
    }
}
