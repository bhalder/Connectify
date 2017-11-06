import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.LinkedList;
import java.util.*;


public class Crawler {

   int max_level = 3;
   Set<String> visited_link_set = new HashSet<String>(); 
   Queue<String> link_set = new LinkedList<String>();
   Queue<Integer> link_set_int = new LinkedList<Integer>();
   LinkSanitizer sanitizer = new LinkSanitizer();
   ContentSanitizer contentSanitizer = new ContentSanitizer();

   public Crawler(String searchString) {
      link_set_int.add(0);
      contentSanitizer.setSearchString(searchString);
   }

   // BFS implementation
   public void crawl(String url) {

      if (visited_link_set.contains(url)) {
         System.out.println("Returning from visited-link");
         return;
      } else {
         visited_link_set.add(url);
      }

      int level = link_set_int.remove();

      if (level == max_level) {
         System.out.println("Returning from level == max_level");
         return;
      }

      System.out.println("== level : [ " + level +" ] ======== URL : "+url);
      try {
         Document doc = Jsoup.connect(url).ignoreContentType(true)
           .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")  
           .referrer("http://www.google.com")   
           .timeout(12000) 
           .followRedirects(true).get();

         if (!contentSanitizer.isGoodContent(doc)) {
            return;
         }

         Elements links = doc.select("a[href]");

         for (Element link : links) {
            String linkUrl  = link.attr("abs:href");
            if (sanitizer.isInterestingLink(linkUrl)) {         
               link_set.add(linkUrl);
               link_set_int.add(level+1);
            }
         }
      } catch (Exception e) {
         System.out.println("Oops! Looks like connection and loading failed");
      }
         
      while( !link_set.isEmpty()) {
         crawl(link_set.remove());
      }
   }
}
