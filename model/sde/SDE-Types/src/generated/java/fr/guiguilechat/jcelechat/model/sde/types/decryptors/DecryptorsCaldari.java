package fr.guiguilechat.jcelechat.model.sde.types.decryptors;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Decryptors;
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
        public static final String RESOURCE_PATH = "SDE/types/decryptors/DecryptorsCaldari.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(DecryptorsCaldari.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DecryptorsCaldari> types;
        }
    }
}
