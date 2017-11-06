public class Runner {

   public static void main(String[] args) {

      Crawler crawler = new Crawler("Barun");
      crawler.crawl("https://www.google.com/search?q="+args[0]);
      
   }
}
