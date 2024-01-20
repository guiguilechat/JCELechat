package fr.guiguilechat.jcelechat.programs.cli.merstats.killdump;

public class ReportAll {

	public static void main(String[] args) {
		ReportIndusHScount.main(args);
		ReportIndusHSValue.main(args);
		ReportNPCPct.main(args);
		ReportStructureHSCount.main(args);
		ReportStructureHSValue.main(args);
		ReportStructureLSCount.main(args);
		ReportStructureLSValue.main(args);
		ReportStructureNSCount.main(args);
		ReportStructureNSValue.main(args);
		ReportStructureWSCount.main(args);
		ReportStructureWSValue.main(args);
		ReportTotalByRegionCount.main(args);
		ReportTotalByRegionValue.main(args);
	}

}
