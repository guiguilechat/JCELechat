package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.infantry.Agents;
import fr.guiguilechat.jcelechat.model.sde.types.infantry.BattleSalvage;
import fr.guiguilechat.jcelechat.model.sde.types.infantry.InfantryColorSkin;
import fr.guiguilechat.jcelechat.model.sde.types.infantry.InfantryDropsuits;
import fr.guiguilechat.jcelechat.model.sde.types.infantry.InfantryEquipment;
import fr.guiguilechat.jcelechat.model.sde.types.infantry.InfantryInstallations;
import fr.guiguilechat.jcelechat.model.sde.types.infantry.InfantryModules;
import fr.guiguilechat.jcelechat.model.sde.types.infantry.InfantrySkillEnhancers;
import fr.guiguilechat.jcelechat.model.sde.types.infantry.InfantrySkills;
import fr.guiguilechat.jcelechat.model.sde.types.infantry.InfantryVehicles;
import fr.guiguilechat.jcelechat.model.sde.types.infantry.InfantryWeapons;
import fr.guiguilechat.jcelechat.model.sde.types.infantry.SalvageContainers;
import fr.guiguilechat.jcelechat.model.sde.types.infantry.SalvageDecryptors;
import fr.guiguilechat.jcelechat.model.sde.types.infantry.Services;
import fr.guiguilechat.jcelechat.model.sde.types.infantry.SurfaceInfrastructure;
import fr.guiguilechat.jcelechat.model.sde.types.infantry.VisualCustomization;
import fr.guiguilechat.jcelechat.model.sde.types.infantry.Warbarge;

public abstract class Infantry
    extends EveType
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
