package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class RogueDroneAnalysisData
    extends Commodity
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final RogueDroneAnalysisData.MetaGroup METAGROUP = new RogueDroneAnalysisData.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<RogueDroneAnalysisData> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<RogueDroneAnalysisData>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/RogueDroneAnalysisData.yaml";
        private Map<Integer, RogueDroneAnalysisData> cache = (null);

        @Override
        public IMetaCategory<? super RogueDroneAnalysisData> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4142;
        }

        @Override
        public String getName() {
            return "RogueDroneAnalysisData";
        }

        @Override
        public synchronized Map<Integer, RogueDroneAnalysisData> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(RogueDroneAnalysisData.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, RogueDroneAnalysisData> types;
        }
    }
}
