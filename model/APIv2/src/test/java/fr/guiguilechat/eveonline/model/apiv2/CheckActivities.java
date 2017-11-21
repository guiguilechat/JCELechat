package fr.guiguilechat.eveonline.model.apiv2;

import java.util.Date;

import fr.guiguilechat.eveonline.model.apiv2.APIRoot;
import fr.guiguilechat.eveonline.model.apiv2.Char;
import fr.guiguilechat.eveonline.model.apiv2.Account.EveChar;
import fr.guiguilechat.eveonline.model.apiv2.Char.JobEntry;

public class CheckActivities {

	public static void main(String[] args) {
		Date now = new Date();
		for (String api : args) {
			APIRoot r = new APIRoot(api);
			for (EveChar c : r.account.characters()) {
				System.out.println("" + c.name);
				for (JobEntry e : r.chars.industryJobs(c.characterID)) {
					if (now.after(e.endDate)) {
						System.out
						.println("\t[" + Char.activityName(e.activityID) + "] " + e.blueprintTypeName);
					}
				}
			}
		}
	}

}
