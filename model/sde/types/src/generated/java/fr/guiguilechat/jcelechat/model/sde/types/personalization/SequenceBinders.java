package fr.guiguilechat.jcelechat.model.sde.types.personalization;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Personalization;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class SequenceBinders
    extends Personalization
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final SequenceBinders.MetaGroup METAGROUP = new SequenceBinders.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<SequenceBinders> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SequenceBinders>
    {
        public static final String RESOURCE_PATH = "SDE/types/personalization/SequenceBinders.yaml";
        private Map<Integer, SequenceBinders> cache = (null);

        @Override
        public IMetaCategory<? super SequenceBinders> category() {
            return Personalization.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4725;
        }

        @Override
        public String getName() {
            return "SequenceBinders";
        }

        @Override
        public synchronized Map<Integer, SequenceBinders> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SequenceBinders.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, SequenceBinders> types;
        }
    }
}
