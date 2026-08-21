import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PageParser {
    public static void main(String[] args) {
        try {
            // Connect to the web page and get its HTML
            Document doc = Jsoup.connect("https://www.example.com/").get();

            // Get all the links on the page
            Elements links = doc.select("a[href]");

            // Print out each link's text and URL
            for (Element link : links) {
                System.out.println("Link Text: " + link.text());
                System.out.println("Link URL: " + link.attr("href"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
