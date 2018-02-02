package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.types.infantry.Agents;
import fr.guiguilechat.eveonline.model.sde.items.types.infantry.BattleSalvage;
import fr.guiguilechat.eveonline.model.sde.items.types.infantry.InfantryColorSkin;
import fr.guiguilechat.eveonline.model.sde.items.types.infantry.InfantryDropsuits;
import fr.guiguilechat.eveonline.model.sde.items.types.infantry.InfantryEquipment;
import fr.guiguilechat.eveonline.model.sde.items.types.infantry.InfantryInstallations;
import fr.guiguilechat.eveonline.model.sde.items.types.infantry.InfantryModules;
import fr.guiguilechat.eveonline.model.sde.items.types.infantry.InfantrySkillEnhancers;
import fr.guiguilechat.eveonline.model.sde.items.types.infantry.InfantrySkills;
import fr.guiguilechat.eveonline.model.sde.items.types.infantry.InfantryVehicles;
import fr.guiguilechat.eveonline.model.sde.items.types.infantry.InfantryWeapons;
import fr.guiguilechat.eveonline.model.sde.items.types.infantry.SalvageContainers;
import fr.guiguilechat.eveonline.model.sde.items.types.infantry.SalvageDecryptors;
import fr.guiguilechat.eveonline.model.sde.items.types.infantry.Services;
import fr.guiguilechat.eveonline.model.sde.items.types.infantry.SurfaceInfrastructure;
import fr.guiguilechat.eveonline.model.sde.items.types.infantry.VisualCustomization;
import fr.guiguilechat.eveonline.model.sde.items.types.infantry.Warbarge;

public abstract class Infantry
    extends Item
{

    @Override
    public int getCategoryId() {
        return  350001;
    }

    @Override
    public Class<?> getCategory() {
        return Infantry.class;
    }

    public static Map<String, ? extends Infantry> loadCategory() {
        return Stream.of(Agents.load(), VisualCustomization.load(), BattleSalvage.load(), InfantryWeapons.load(), InfantryModules.load(), InfantrySkills.load(), InfantryDropsuits.load(), InfantrySkillEnhancers.load(), SalvageDecryptors.load(), InfantryEquipment.load(), SurfaceInfrastructure.load(), Warbarge.load(), InfantryVehicles.load(), Services.load(), SalvageContainers.load(), InfantryInstallations.load(), InfantryColorSkin.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
