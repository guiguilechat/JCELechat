package fr.guiguilechat.jcelechat.model.sde.items.types.decryptors;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Decryptors;
import org.yaml.snakeyaml.Yaml;

public class DecryptorsCaldari
    extends Decryptors
{
    public static final DecryptorsCaldari.MetaGroup METAGROUP = new DecryptorsCaldari.MetaGroup();

    @Override
    public IMetaGroup<DecryptorsCaldari> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DecryptorsCaldari>
    {
        public static final String RESOURCE_PATH = "SDE/items/decryptors/DecryptorsCaldari.yaml";
        private Map<String, DecryptorsCaldari> cache = (null);

        @Override
        public IMetaCategory<? super DecryptorsCaldari> category() {
            return Decryptors.METACAT;
        }

        @Override
        public int getGroupId() {
            return  731;
        }

        @Override
        public String getName() {
            return "DecryptorsCaldari";
        }

        @Override
        public synchronized Map<String, DecryptorsCaldari> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DecryptorsCaldari.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DecryptorsCaldari> items;
        }
    }
}
