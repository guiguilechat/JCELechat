package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class CyberElectronicsImplantBlueprints
    extends Blueprint
{
    public static final CyberElectronicsImplantBlueprints.MetaGroup METAGROUP = new CyberElectronicsImplantBlueprints.MetaGroup();

    @Override
    public IMetaGroup<CyberElectronicsImplantBlueprints> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CyberElectronicsImplantBlueprints>
    {
        public static final String RESOURCE_PATH = "SDE/items/blueprint/CyberElectronicsImplantBlueprints.yaml";
        private Map<String, CyberElectronicsImplantBlueprints> cache = (null);

        @Override
        public IMetaCategory<? super CyberElectronicsImplantBlueprints> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  2023;
        }

        @Override
        public String getName() {
            return "CyberElectronicsImplantBlueprints";
        }

        @Override
        public synchronized Map<String, CyberElectronicsImplantBlueprints> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(CyberElectronicsImplantBlueprints.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, CyberElectronicsImplantBlueprints> items;
        }
    }
}
