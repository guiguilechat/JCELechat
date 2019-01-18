package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.infantry.Agents;
import fr.guiguilechat.jcelechat.model.sde.items.types.infantry.BattleSalvage;
import fr.guiguilechat.jcelechat.model.sde.items.types.infantry.InfantryColorSkin;
import fr.guiguilechat.jcelechat.model.sde.items.types.infantry.InfantryDropsuits;
import fr.guiguilechat.jcelechat.model.sde.items.types.infantry.InfantryEquipment;
import fr.guiguilechat.jcelechat.model.sde.items.types.infantry.InfantryInstallations;
import fr.guiguilechat.jcelechat.model.sde.items.types.infantry.InfantryModules;
import fr.guiguilechat.jcelechat.model.sde.items.types.infantry.InfantrySkillEnhancers;
import fr.guiguilechat.jcelechat.model.sde.items.types.infantry.InfantrySkills;
import fr.guiguilechat.jcelechat.model.sde.items.types.infantry.InfantryVehicles;
import fr.guiguilechat.jcelechat.model.sde.items.types.infantry.InfantryWeapons;
import fr.guiguilechat.jcelechat.model.sde.items.types.infantry.SalvageContainers;
import fr.guiguilechat.jcelechat.model.sde.items.types.infantry.SalvageDecryptors;
import fr.guiguilechat.jcelechat.model.sde.items.types.infantry.Services;
import fr.guiguilechat.jcelechat.model.sde.items.types.infantry.SurfaceInfrastructure;
import fr.guiguilechat.jcelechat.model.sde.items.types.infantry.VisualCustomization;
import fr.guiguilechat.jcelechat.model.sde.items.types.infantry.Warbarge;

public abstract class Infantry
    extends Item
{
    public static final Infantry.MetaCat METACAT = new Infantry.MetaCat();

    @Override
    public IMetaCategory<Infantry> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Infantry>
    {

        @Override
        public int getCategoryId() {
            return  350001;
        }

        @Override
        public String getName() {
            return "Infantry";
        }

        @Override
        public Collection<IMetaGroup<? extends Infantry>> groups() {
            return Arrays.asList(InfantryWeapons.METAGROUP, InfantryDropsuits.METAGROUP, InfantryModules.METAGROUP, InfantryVehicles.METAGROUP, InfantrySkills.METAGROUP, InfantryEquipment.METAGROUP, InfantrySkillEnhancers.METAGROUP, InfantryInstallations.METAGROUP, SurfaceInfrastructure.METAGROUP, Services.METAGROUP, Agents.METAGROUP, VisualCustomization.METAGROUP, SalvageContainers.METAGROUP, SalvageDecryptors.METAGROUP, BattleSalvage.METAGROUP, Warbarge.METAGROUP, InfantryColorSkin.METAGROUP);
        }
    }
}
