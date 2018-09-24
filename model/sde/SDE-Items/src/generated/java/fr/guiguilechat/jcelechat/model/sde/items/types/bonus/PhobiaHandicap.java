package fr.guiguilechat.jcelechat.model.sde.items.types.bonus;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Bonus;
import org.yaml.snakeyaml.Yaml;

public class PhobiaHandicap
    extends Bonus
{
    public final static PhobiaHandicap.MetaGroup METAGROUP = new PhobiaHandicap.MetaGroup();

    @Override
    public IMetaGroup<PhobiaHandicap> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PhobiaHandicap>
    {
        public final static String RESOURCE_PATH = "SDE/items/bonus/PhobiaHandicap.yaml";
        private Map<String, PhobiaHandicap> cache = (null);

        @Override
        public IMetaCategory<? super PhobiaHandicap> category() {
            return Bonus.METACAT;
        }

        @Override
        public int getGroupId() {
            return  193;
        }

        @Override
        public String getName() {
            return "PhobiaHandicap";
        }

        @Override
        public synchronized Map<String, PhobiaHandicap> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(PhobiaHandicap.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PhobiaHandicap> items;
        }
    }
}
