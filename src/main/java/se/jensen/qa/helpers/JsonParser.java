package se.jensen.qa.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.text.StrSubstitutor;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JsonParser {
	private static ObjectMapper mapper = new ObjectMapper();
	
	private static  Map<String, String> getPlaceHolders() {
		Map<String, String> map = new HashMap<>();
		map.put("CURRENT_DATE_TIME", getCurrentTimeStamp());
		return map;
	}

	private static String getCurrentTimeStamp() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd-HHmmss");//dd/MM/yyyy
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}

	private static String readFile(String filePath) throws IOException {
		ClassLoader classLoader = JsonParser.class.getClassLoader();
		File file = new File(classLoader.getResource(filePath).getFile());
		byte[] encoded = Files.readAllBytes(Paths.get(file.getPath()));
		String template = new String(encoded, "UTF-8");
		return StrSubstitutor.replace(template, getPlaceHolders());
	}

	public static <T> T jsonToBean(String filePath, Class<T> type)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(readFile(filePath), type);
	}

	public static <T> List<T> jsonToBeanList(String filePath, Class<T[]> type)
			throws JsonParseException, JsonMappingException, IOException {
		return Arrays.asList(mapper.readValue(readFile(filePath), type));
	}

	public static Map<String, Map<String, String>> jsonToTwoLevelMap(String filePath)
			throws JsonParseException, JsonMappingException, IOException {
		TypeReference<HashMap<String, HashMap<String, String>>> typeRef = new TypeReference<HashMap<String, HashMap<String, String>>>() {
		};
		return mapper.readValue(readFile(filePath), typeRef);
	}

}
