package services;

public class URLClass {
	
	private static String url = null;

	public static  String getUrl(String environment){
		
		switch (environment.toUpperCase().trim()) {
		case "STAGING":
			url = "https://sureifylife.com/carrier";
			break;
		case "BETA":
			url = "https://sureify.com/beta/carrier";
			break;
		case "ALPHA":
			url = "https://sureify.com/alpha/carrier";
			break;
		default:
		   url = null;
		}
	
		return url;
	}
}
