package fr.guiguilechat.jcelechat.model.sde.types.bonus;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Bonus;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class PhobiaHandicap
    extends Bonus
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final PhobiaHandicap.MetaGroup METAGROUP = new PhobiaHandicap.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<PhobiaHandicap> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PhobiaHandicap>
    {
        public static final String RESOURCE_PATH = "SDE/types/bonus/PhobiaHandicap.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(PhobiaHandicap.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<String, PhobiaHandicap> types;
        }
    }
}
