package fr.guiguilechat.jcelechat.model.sde.types.qaanddevgroups;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.IgnoreMiningWaste;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.types.QAAndDevGroups;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class QAPhasedAsteroids
    extends QAAndDevGroups
{
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {IgnoreMiningWaste.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill1 .INSTANCE })));
    public static final QAPhasedAsteroids.MetaGroup METAGROUP = new QAPhasedAsteroids.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<QAPhasedAsteroids> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<QAPhasedAsteroids>
    {
        public static final String RESOURCE_PATH = "SDE/types/qaanddevgroups/QAPhasedAsteroids.yaml";
        private Map<Integer, QAPhasedAsteroids> cache = (null);

        @Override
        public IMetaCategory<? super QAPhasedAsteroids> category() {
            return QAAndDevGroups.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4941;
        }

        @Override
        public String getName() {
            return "QAPhasedAsteroids";
        }

        @Override
        public synchronized Map<Integer, QAPhasedAsteroids> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(QAPhasedAsteroids.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, QAPhasedAsteroids> types;
        }
    }
}
