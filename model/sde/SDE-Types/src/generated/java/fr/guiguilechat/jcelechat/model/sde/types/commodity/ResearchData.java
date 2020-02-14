package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class ResearchData
    extends Commodity
{
    public static final ResearchData.MetaGroup METAGROUP = new ResearchData.MetaGroup();

    @Override
    public IMetaGroup<ResearchData> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ResearchData>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/ResearchData.yaml";
        private Map<String, ResearchData> cache = (null);

        @Override
        public IMetaCategory<? super ResearchData> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1141;
        }

        @Override
        public String getName() {
            return "ResearchData";
        }

        @Override
        public synchronized Map<String, ResearchData> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ResearchData.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, ResearchData> types;
        }
    }
}
