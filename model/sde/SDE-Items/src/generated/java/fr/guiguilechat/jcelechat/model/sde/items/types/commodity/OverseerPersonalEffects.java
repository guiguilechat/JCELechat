package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class OverseerPersonalEffects
    extends Commodity
{
    public final static OverseerPersonalEffects.MetaGroup METAGROUP = new OverseerPersonalEffects.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/commodity/OverseerPersonalEffects.yaml";
    private static Map<String, OverseerPersonalEffects> cache = (null);

    @Override
    public int getGroupId() {
        return  493;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<OverseerPersonalEffects> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, OverseerPersonalEffects> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(OverseerPersonalEffects.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, OverseerPersonalEffects> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<OverseerPersonalEffects>
    {

        @Override
        public MetaCategory<? super OverseerPersonalEffects> category() {
            return Commodity.METACAT;
        }

        @Override
        public String getName() {
            return "OverseerPersonalEffects";
        }

        @Override
        public Collection<OverseerPersonalEffects> items() {
            return (load().values());
        }
    }
}
