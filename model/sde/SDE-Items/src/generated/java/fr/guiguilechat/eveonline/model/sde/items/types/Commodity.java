package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.AccelerationGateKeys;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.AdvancedCapitalConstructionComponents;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.ArtifactsAndPrototypes;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.Biohazard;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.CapitalConstructionComponents;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.Commodities;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.ConstructionComponents;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.CriminalTags;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.DataInterfaces;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.Datacores;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.DecryptorsSleepers;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.DecryptorsTakmahl;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.DecryptorsTalocan;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.DecryptorsYanJung;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.DepricatedSubsystems;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.Drugs;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.EmpireBountyReimbursementTags;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.EmpireInsigniaDrops;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.Frozen;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.General;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.HybridTechComponents;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.Identification;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.Lease;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.Livestock;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.MaterialsAndCompounds;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.Miscellaneous;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.ObsoleteBooks;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.OverseerPersonalEffects;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.Radioactive;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.Refinables;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.ResearchData;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.SecurityTags;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.ShipLogs;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.SlaveReception;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.SleeperComponents;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.StationComponents;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.StrongBoxes;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.SurfaceInfrastructurePrefabUnits;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.TechnicalDataChips;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.Tool;
import fr.guiguilechat.eveonline.model.sde.items.types.commodity.UnknownComponents;

public abstract class Commodity
    extends Item
{

    @Override
    public int getCategoryId() {
        return  17;
    }

    @Override
    public Class<?> getCategory() {
        return Commodity.class;
    }

    public static Map<String, ? extends Commodity> loadCategory() {
        return Stream.of(EmpireBountyReimbursementTags.load(), EmpireInsigniaDrops.load(), UnknownComponents.load(), DecryptorsYanJung.load(), Biohazard.load(), CriminalTags.load(), SlaveReception.load(), DecryptorsTalocan.load(), Datacores.load(), Commodities.load(), AdvancedCapitalConstructionComponents.load(), DecryptorsSleepers.load(), ConstructionComponents.load(), DecryptorsTakmahl.load(), Identification.load(), HybridTechComponents.load(), Livestock.load(), ResearchData.load(), SecurityTags.load(), General.load(), DataInterfaces.load(), Miscellaneous.load(), AccelerationGateKeys.load(), Tool.load(), MaterialsAndCompounds.load(), DepricatedSubsystems.load(), Drugs.load(), OverseerPersonalEffects.load(), ArtifactsAndPrototypes.load(), CapitalConstructionComponents.load(), SleeperComponents.load(), TechnicalDataChips.load(), Refinables.load(), ObsoleteBooks.load(), SurfaceInfrastructurePrefabUnits.load(), Radioactive.load(), StationComponents.load(), StrongBoxes.load(), Frozen.load(), Lease.load(), ShipLogs.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
