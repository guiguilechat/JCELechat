package fr.guiguilechat.jcelechat.model.sde.items.types.sovereigntystructures;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.SovereigntyStructures;
import org.yaml.snakeyaml.Yaml;

public class InfrastructureHub
    extends SovereigntyStructures
{
    /**
     * How many meters from the standard warp-in distance a planet can be anchored from.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100000)
    public int PlanetAnchorDistance;
    public final static InfrastructureHub.MetaGroup METAGROUP = new InfrastructureHub.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  865 :
            {
                return PlanetAnchorDistance;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<InfrastructureHub> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<InfrastructureHub>
    {
        public final static String RESOURCE_PATH = "SDE/items/sovereigntystructures/InfrastructureHub.yaml";
        private Map<String, InfrastructureHub> cache = (null);

        @Override
        public IMetaCategory<? super InfrastructureHub> category() {
            return SovereigntyStructures.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1012;
        }

        @Override
        public String getName() {
            return "InfrastructureHub";
        }

        @Override
        public synchronized Map<String, InfrastructureHub> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(InfrastructureHub.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, InfrastructureHub> items;
        }
    }
}
