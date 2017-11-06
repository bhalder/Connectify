import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import java.util.ArrayList;

public class ContentSanitizer {
   HtmlToPlainText formatter = new HtmlToPlainText();
   String[] selectors = { "p", "about" };
   String searchString;

   public void setSearchString(String searchMe) {
      this.searchString = searchMe;
   }

   boolean isGoodContent(Document doc) {
      ArrayList<String> retStrList;
      for(String selector : selectors) {
         retStrList = HtmlToPlainText.getUsefulText(doc, selector);
         
         if (retStrList != null) {
            for (String s : retStrList) {
               if (s.contains(this.searchString)) {
                  return true;
               }
            } 
         }
      }
 
      return false;
   }

}
