package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class CyberScanningImplantBlueprints
    extends Blueprint
{
    public final static CyberScanningImplantBlueprints.MetaGroup METAGROUP = new CyberScanningImplantBlueprints.MetaGroup();

    @Override
    public IMetaGroup<CyberScanningImplantBlueprints> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CyberScanningImplantBlueprints>
    {
        public final static String RESOURCE_PATH = "SDE/items/blueprint/CyberScanningImplantBlueprints.yaml";
        private Map<String, CyberScanningImplantBlueprints> cache = (null);

        @Override
        public IMetaCategory<? super CyberScanningImplantBlueprints> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1948;
        }

        @Override
        public String getName() {
            return "CyberScanningImplantBlueprints";
        }

        @Override
        public synchronized Map<String, CyberScanningImplantBlueprints> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(CyberScanningImplantBlueprints.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, CyberScanningImplantBlueprints> items;
        }
    }
}
