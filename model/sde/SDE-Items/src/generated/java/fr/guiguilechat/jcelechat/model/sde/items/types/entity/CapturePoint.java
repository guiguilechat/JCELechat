package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class CapturePoint
    extends Entity
{
    public final static CapturePoint.MetaGroup METAGROUP = new CapturePoint.MetaGroup();

    @Override
    public IMetaGroup<CapturePoint> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CapturePoint>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/CapturePoint.yaml";
        private Map<String, CapturePoint> cache = (null);

        @Override
        public IMetaCategory<? super CapturePoint> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  922;
        }

        @Override
        public String getName() {
            return "CapturePoint";
        }

        @Override
        public synchronized Map<String, CapturePoint> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(CapturePoint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, CapturePoint> items;
        }
    }
}
