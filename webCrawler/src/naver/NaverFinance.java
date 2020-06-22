package naver;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * jsoup 필요!
 * python -> beautifulsoup4, selenium : Anaconda(패키지 매니저)
 * 
 */

public class NaverFinance {

	// static 붙이는 이유
	// 일반메서드면 static 붙이지 않아도 됨.
	// static 메서드에서는 static 전역변수만 사용 가능.

	static String base = "https://finance.naver.com/item/frgn.nhn?code=005930&page=1";

	// throws : 예외 발생시 내가 처리하지 않고 나를 호출한 곳에 던지겠다!
	public static void main(String[] args) throws IOException {
		// base 사이트로 가서 전체 페이지의 소스코드를 가져옴.

		Document doc = Jsoup.connect(base).get();
		Elements line = doc.select("table.type2 > tbody > tr");
		System.out.println(line.size());

		int cnt = 0;
		
		for (Element element : line) { // 향상된 for문(for-each문)
			
			Elements tds = element.select("td");
			
			if(tds.size() == 9) {
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				
				// 날짜
				String regdate = tds.get(0).text();
				System.out.println("날짜: " + regdate);
			
				// 종가
				String price = tds.get(1).text();
				System.out.println("종가: " + price);
				
				// 전일비
				String before = tds.get(2).text();
				System.out.println("전일비: " + before);
				
				// 등락률
				String fluctuation = tds.get(3).text();
				System.out.println("종가: " + fluctuation);
				
				// 거래량
				String volume = tds.get(4).text();
				System.out.println("거래량: " + volume);
			
				// 기관-순매매량
				String agencyNetSales = tds.get(5).text();
				System.out.println("순매매량(기관): " + agencyNetSales);
				
				// 순매매량
				String foreignerNetSales = tds.get(6).text();
				System.out.println("순매매량(외국인): " + foreignerNetSales);
				
				// 보유주수
				String sharesHeld = tds.get(7).text();
				System.out.println("보유주수: " + sharesHeld);
				
				// 보유율
				String retentionRate = tds.get(8).text();
				System.out.println("보유율: " + retentionRate);
			
	
			}
		}
		

	}
}
