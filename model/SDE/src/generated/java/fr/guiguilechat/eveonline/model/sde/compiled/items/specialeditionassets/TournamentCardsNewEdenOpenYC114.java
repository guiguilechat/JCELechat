
package fr.guiguilechat.eveonline.model.sde.compiled.items.specialeditionassets;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.SpecialEditionAssets;
import org.yaml.snakeyaml.Yaml;

public class TournamentCardsNewEdenOpenYC114
    extends SpecialEditionAssets
{

    public final static String RESOURCE_PATH = "SDE/specialeditionassets/TournamentCardsNewEdenOpenYC114.yaml";
    private static LinkedHashMap<String, TournamentCardsNewEdenOpenYC114> cache = (null);

    @Override
    public int getGroupId() {
        return  1195;
    }

    @Override
    public Class<?> getGroup() {
        return TournamentCardsNewEdenOpenYC114 .class;
    }

    public static LinkedHashMap<String, TournamentCardsNewEdenOpenYC114> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, TournamentCardsNewEdenOpenYC114> items;

    }

}
