import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;

public class Crawler {
   Set<String> visited_link_set = new HashSet<String>(); 

   // DFS implementation
   public void crawl(String url, int max_level, int level) {
      Set<String> link_set = new HashSet<String>(); 
      if (level == max_level) {
         return;
      }

      if (visited_link_set.contains(url)) {
         return;
      } else {
         visited_link_set.add(url);
      }

      System.out.println("======= Links at level : " + level +" ========");
      try {
         Document doc = Jsoup.connect(url).ignoreContentType(true)
           .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")  
           .referrer("http://www.google.com")   
           .timeout(12000) 
           .followRedirects(true).get();

         Elements links = doc.select("a[href]");

         for (Element link : links) {
            link_set.add(link.attr("abs:href"));
         }
      } catch (Exception e) {
         System.out.println("Oops! Looks like connection and loading failed");
      }
         
      System.out.println("Hash Set is : \n" + link_set);

      Iterator<String> i = link_set.iterator();

      while (i.hasNext()) {
         crawl(i.next(), max_level, level+1);
      }
   }
}
