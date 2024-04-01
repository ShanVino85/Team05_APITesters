package api.utils;

public class IdHolder {

    private static String token;
    private static String userId = "U6493";

    public static String getToken() {
        if (token == null) {
            System.out.println("Token is null.");
        }
        return token;
    }

    public static void setToken(String newToken) {
        token = newToken;
    }
    public static String getUserId() {
        if (userId == null) {
            System.out.println("User ID is null.");
        }
        return userId;
    }

    public static void setUserId(String newUserId) {
    	
        userId = newUserId;
       
    }

	
   
}







//package api.utils;
//
//public class TokenHolder {
//
//	public static String token;
//}
