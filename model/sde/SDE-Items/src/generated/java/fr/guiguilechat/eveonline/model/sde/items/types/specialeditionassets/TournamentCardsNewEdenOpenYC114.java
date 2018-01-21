
package fr.guiguilechat.eveonline.model.sde.items.types.specialeditionassets;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.SpecialEditionAssets;
import org.yaml.snakeyaml.Yaml;

public class TournamentCardsNewEdenOpenYC114
    extends SpecialEditionAssets
{

    public final static String RESOURCE_PATH = "SDE/items/specialeditionassets/TournamentCardsNewEdenOpenYC114.yaml";
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
                cache = new Yaml().loadAs(new InputStreamReader(TournamentCardsNewEdenOpenYC114 .class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, TournamentCardsNewEdenOpenYC114> items;

    }

}
