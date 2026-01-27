import fr.guiguilechat.jcelechat.libs.everef.history.EverefHistoryFetcher;
import fr.guiguilechat.jcelechat.libs.everef.history.HistoryEntry;

void main() throws InterruptedException, ExecutionException {

	EverefHistoryFetcher fetcher = new EverefHistoryFetcher("test-guiguilechat");
	CompletableFuture<List<HistoryEntry>> f_histories = fetcher.fetch(2023, 1, 2);
	CompletableFuture<List<HistoryEntry>> f_histories2 = fetcher.fetch(2020, 12, 25);
	List<HistoryEntry> histories = f_histories.get();
	List<HistoryEntry> histories2 = f_histories2.get();

	System.out.println("received " + histories.size() + " histories");
	if (!histories.isEmpty()) {
		System.out.println(" first is " + histories.get(0).dateInstant());
	}
	System.out.println("received " + histories2.size() + " histories");
	if (!histories2.isEmpty()) {
		System.out.println(" first is " + histories2.get(0).dateInstant());
	}
}