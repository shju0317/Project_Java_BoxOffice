package naver;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NaverBoxOffice {
	public static void main(String[] args) throws IOException {
		String url = "https://movie.naver.com/movie/running/current.nhn";
		
		String title = "";		// 영화제목
		String score = "";		// 평점
		String bookRate = "";	// 예매율
		String genre = "";		// 장르
		String actor = "";		// 출연진
		String runTime = "";	// 상영시간
		String release = "";	// 개봉일
		String director = "";	// 감독
		String naverCode = "";  // 네이버 영화코드
		
		Document doc = Jsoup.connect(url).get();	
		Elements movieList = doc.select("ul.lst_detail_t1 > li > dl.lst_dsc");
		
		// System.out.println(line.size());
	
		// Python -> for element in movieList:
		for (Element movie : movieList) {
			bookRate = "0";
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			
		    title = movie.select("dt.tit > a").text(); // 영화제목
		    
		    score = movie.select("span.num").get(0).text(); // 평점
		    
			if(movie.select("span.num").size() == 2) { //예매율 ->  결측치(N/A)
				bookRate = movie.select("span.num").get(1).text();
			}
			
			genre = movie.select("dl.info_txt1 span.link_txt").get(0).text(); // 장르
			
			actor = movie.select("dl.info_txt1 span.link_txt[1] > a").text(); // 출연진
			
			runTime = movie.select("dl.info_txt1 > dd").text(); // 상영시간
			
			String temp = movie.select("dl.info_txt1 > dd").text(); // 상영시간
			
			int beginTimeIndex = temp.indexOf("|");
			int endTimeIndex = temp.lastIndexOf("|");
			
			if(beginTimeIndex == endTimeIndex) {
				runTime = temp.substring(0, endTimeIndex);
			}else {
				runTime = temp.substring(beginTimeIndex+2, endTimeIndex);
			}
			
//			 String[] runTimeDataList = temp.split(" | ");
//			 runTime = runTimeDataList[2];
			// release = runTimeDataList[4];
			
			//release = movie.select("dl.info_txt1 span.split").get(1).text(); // 개봉날짜
			
			int releaseTxtIndex = temp.lastIndexOf("개봉"); // 개봉날짜
			release = temp.substring(endTimeIndex+2, releaseTxtIndex);
			
			
			//System.out.println(movie.select("dt.tit_t2")); // 감독
			//System.out.println(movie.select("dt.tit_t3")); // 출연진
			
			actor = "";
			director = "";
			
			// 0:없음 1:있음
			int dCode = 0; // 감독 유무 확인
			int aCode = 0; // 출연진 유무 확인
			
			if(!movie.select("dt.tit_t2").text().equals("")) {
				dCode = 1; // 감독 있음
			}
			
			if(!movie.select("dt.tit_t3").text().equals("")) {
				aCode = 1; // 출연진 있음
			}
			
			if(dCode == 1 && aCode == 1) {
				director = movie.select("dd > span.link_txt").get(1).text(); // 감독
				actor = movie.select("dd > span.link_txt").get(2).text(); // 출연진
			}else if(dCode == 0 && aCode == 1) {
				actor = movie.select("dd > span.link_txt").get(1).text(); // 출연진
			}else if(dCode == 1 && aCode == 0) {
				director = movie.select("dd > span.link_txt").get(1).text(); // 감독
			}
			
			String naverHref = movie.select("dt.tit > a").attr("href");
			naverCode = naverHref.substring(naverHref.lastIndexOf("=")+1); // 네이버 영화코드
			
			System.out.println("영화제목: " + title);
			System.out.println("평점: " + score);
			System.out.println("예매율: " + bookRate + "%");
			System.out.println("장르: " + genre);
			System.out.println("출연진: " + actor);
			System.out.println("상영시간: " + runTime);
			System.out.println("개봉날짜: " + release);
			System.out.println("감독: " + director);
			System.out.println("무비코드: " + naverCode);
			
		}
		
	}
}
