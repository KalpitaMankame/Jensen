package se.jensen.qa.helpers;

import java.util.ResourceBundle;

public class JensenEnvConstants {
	 private static ResourceBundle jensenEnv = ResourceBundle.getBundle("jensen-env");
	 public static final String BASE_URL = jensenEnv.getString("jensen.base.url");
}
