import java.lang.*;

public class LinkSanitizer {

   private String[] whiteList = { "linkedin.com", "quora.com"
                                 ,"facebook.com", "twitter.com"
                                 ,"orkut.com", "instagram.com"
                                 ,"tumblr.com", "plus.google.com"
                                 ,"pintrest.com"};

   boolean isInterestingLink(String url) {
      
      for(String str : whiteList) {
         if (url.contains(str)) {
            return true;
         }
      }
      return false;
   }

}
