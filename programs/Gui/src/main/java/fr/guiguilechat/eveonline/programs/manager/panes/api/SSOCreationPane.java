package fr.guiguilechat.eveonline.programs.manager.panes.api;

import java.util.regex.Pattern;

import fr.guiguilechat.eveonline.model.esi.connect.ESIConnection;
import fr.guiguilechat.eveonline.model.esi.connect.ESIConnection.SCOPES;
import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import javafx.application.Platform;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SSOCreationPane extends Accordion implements EvePane {

	protected final Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	protected EvePane[] subPanes;
	protected TitledPane devAPIPane;
	protected TitledPane refreshTokenPane;

	protected TextField appIDField = new TextField();
	protected TextField appKeyField = new TextField();
	protected TextField baseField = new TextField();
	protected TextField refreshTokenField = new TextField();

	protected Label characterName = new Label();

	@Override
	public EvePane[] subEvePanes() {
		return subPanes;
	}

	protected static String localcallback = "http://localhost/callback/";

	public SSOCreationPane(Manager parent) {
		this.parent = parent;

		VBox devapibox = new VBox();
		Label devExplanation = new Label("You first need to create a dev app.\nOn CCP's dev app page:\n"
				+ " - log on that site\n" + " - accept the license\n" + " - create a dev APP\n" + "   - name/des don't matter"
				+ "   - connection type must be authentification & api access" + "   - give it all the permissions\n"
				+ "   - set the callback to " + localcallback + "\n"
				+ " - copy the app's key and code to your clipboard\n"
				+ "   You can also paste your app id/key in the fields below."
				+ " - click the validate button to go to next step");
		Button webPageButn = new Button("go to CCP's dev app page");
		webPageButn.setOnAction(e -> {
			appIDField.setText(null);
			appKeyField.setText(null);
			new Thread(this::getAppIds).start();
		});
		Button validAppIdKeyBtn = new Button("validate app");
		validAppIdKeyBtn.setOnAction(e -> changeToRefreshToken());
		devapibox.getChildren().addAll(devExplanation, webPageButn, new HBox(new Label("app ID "), appIDField),
				new HBox(new Label("app key "), appKeyField), validAppIdKeyBtn);
		devAPIPane = new TitledPane("dev api", devapibox);

		VBox refreshTokenBox = new VBox();
		Label refreshExplanation = new Label(
				"We then connect your account to your app.\n" + "Next button will ask you to accept the connection.\n"
						+ "Once accepted, you will be redirected to an error page\n" + "Copy the page address in the clipboard.");
		Button webConnectBtn = new Button("connect your account to the app");
		webConnectBtn.setOnAction(e -> {
			refreshTokenField.setText(null);
			new Thread(this::getRefreshToken).start();
		});
		Button testConnection = new Button("test basic+refresh");
		testConnection.setOnAction(e -> new Thread(this::testConnection).start());
		Button addAPI = new Button("add this api");
		addAPI.setOnAction(e -> addAPI());
		refreshTokenBox.getChildren().addAll(refreshExplanation,
				new HBox(new Label("app id created from previous step"), baseField),
				webConnectBtn, new HBox(new Label("refresh token"), refreshTokenField), testConnection, characterName, addAPI);
		refreshTokenPane = new TitledPane("refresh token", refreshTokenBox);

		getPanes().addAll(devAPIPane, refreshTokenPane);
		setExpandedPane(devAPIPane);
	}

	protected Thread thread = null;

	protected static final Pattern appIdPat = Pattern.compile("^[0-9a-fA-F]{32}$");
	protected static final Pattern appKeyPat = Pattern.compile("^[0-9a-zA-Z]{40}$");

	protected void getAppIds() {
		if (thread != null) {
			thread.interrupt();
		}
		thread = Thread.currentThread();
		ESIConnection.openBrowserForDevAPI();
		boolean correct = false;
		while(!correct) {
			String entry = ESIConnection.extractStringFromClipboard();
			if(entry!=null) {
				if (appIdPat.matcher(entry).matches()) {
					appIDField.setText(entry);
				} else if (appKeyPat.matcher(entry).matches()) {
					appKeyField.setText(entry);
				}
			} else {
				return;
			}
			correct = appIDField.getText() != null && appKeyField.getText() != null;
		}
		thread = null;
	}

	protected void changeToRefreshToken() {
		String appID = appIDField.getText(), appKey = appKeyField.getText();
		if (appID != null && appIdPat.matcher(appID).matches() && appKey != null && appKeyPat.matcher(appKey).matches()) {
			baseField.setText(ESIConnection.encode(appID, appKey));
			setExpandedPane(refreshTokenPane);
		}
	}

	protected void getRefreshToken() {
		if (thread != null) {
			thread.interrupt();
		}
		thread = Thread.currentThread();
		String authCode = ESIConnection.getCodeByClipboard(appIDField.getText(), localcallback, SCOPES.values());
		if (authCode != null) {
			refreshTokenField.setText(ESIConnection.getRefreshToken(baseField.getText(), authCode));
		}
	}

	protected void testConnection() {
		String base = baseField.getText();
		String refresh = refreshTokenField.getText();
		if (base != null && refresh != null) {
			String charName = new ESIConnection(base, refresh).verify();
			Platform.runLater(() -> characterName.setText(charName));
		} else {
			characterName.setText("invalid base/refresh");
		}
	}

	protected void addAPI() {
		String base = baseField.getText();
		String refresh = refreshTokenField.getText();
		parent.addAPI(base, refresh);
	}

}
