package daum;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DaumBoxOffice{
	public static void main(String[] args) throws IOException{
		String baseUrl = "http://ticket2.movie.daum.net/Movie/MovieRankList.aspx";
		Document doc = Jsoup.connect(baseUrl).get();
		
		Elements movieList = doc.select("ul.list_boxthumb > li > a");
		
		for(Element movie : movieList) {
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			
			String url = movie.attr("href");
			// System.out.println(movie.attr("href"));
			Document movieDoc = Jsoup.connect(url).get();
			
			if(movieDoc.select("span.txt_name").size() == 0) {
				continue;
			}
			
			String title = movieDoc.select("span.txt_name").get(0).text(); // 영화제목(년도)
			String onlyTitle = title.substring(0, title.lastIndexOf("(")); // 영화제목
			
			
			String daumHref = movieDoc.select("a.area_poster").get(0).attr("href"); 
			String daumCode = daumHref.substring(daumHref.lastIndexOf("=")+1, daumHref.lastIndexOf("#")); // 영화코드
			

			
			System.out.println("영화제목: " + onlyTitle);
			System.out.println("URL: " + daumCode);
//			System.out.println("예매율: " + bookRate + "%");
//			System.out.println("장르: " + genre);
//			System.out.println("출연진: " + actor);
//			System.out.println("상영시간: " + runTime);
//			System.out.println("개봉날짜: " + release);
//			System.out.println("감독: " + director);
//			System.out.println("무비코드: " + naverCode);
			
		}
	}
}
