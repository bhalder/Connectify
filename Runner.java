public class Runner {

   public static void main(String[] args) {

      Crawler crawler = new Crawler();
      crawler.crawl(args[0], 3, 0);
      
   }
}
