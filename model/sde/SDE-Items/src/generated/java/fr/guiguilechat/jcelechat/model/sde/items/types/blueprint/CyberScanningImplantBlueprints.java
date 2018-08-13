package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class CyberScanningImplantBlueprints
    extends Blueprint
{
    public final static CyberScanningImplantBlueprints.MetaGroup METAGROUP = new CyberScanningImplantBlueprints.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/blueprint/CyberScanningImplantBlueprints.yaml";
    private static Map<String, CyberScanningImplantBlueprints> cache = (null);

    @Override
    public int getGroupId() {
        return  1948;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<CyberScanningImplantBlueprints> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, CyberScanningImplantBlueprints> load() {
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

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<CyberScanningImplantBlueprints>
    {

        @Override
        public MetaCategory<? super CyberScanningImplantBlueprints> category() {
            return Blueprint.METACAT;
        }

        @Override
        public String getName() {
            return "CyberScanningImplantBlueprints";
        }

        @Override
        public Collection<CyberScanningImplantBlueprints> items() {
            return (load().values());
        }
    }
}
