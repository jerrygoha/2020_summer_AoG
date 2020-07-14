package com.o2o.action.server.app;

import com.google.actions.api.*;
import com.google.actions.api.Capability;
import com.google.actions.api.response.ResponseBuilder;
import com.google.actions.api.response.helperintent.SelectionCarousel;
import com.google.api.services.actions_fulfillment.v2.model.*;
import com.o2o.action.server.util.CommonUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class internApp extends DialogflowApp {

//	public ArrayList<exData> readExcel() throws FileNotFoundException {
//
//		String path = internApp.class.getResource("").getPath();	//파일 경로
//		ArrayList<exData> list = new ArrayList<exData>();
//
//		try{
//			File file = new File(path + "TEAM SCHEDULE.xlsx");
//
//			FileInputStream fis = new FileInputStream(file);
//			XSSFWorkbook workbook = new XSSFWorkbook(fis);
//
//			//엑셀 index는 0부터 시작
//			int rowindex = 0;
//			int colindex = 0;
//
//			//시트수
//			XSSFSheet sheet = workbook.getSheet(0);
//			//행의 수
//			int rows = sheet.getPhysicalNumberOfRows();
//			for(rowindex = 2; rowindex<rows; rowindex++){
//
//				exData ed = new exData();
//
//				//행 읽기
//				XSSFRow row = sheet.getRow(rowindex);
//				XSSFCell cell = row.getCell(2);
//
//				ed.setName(String.valueOf(row.getCell(0)));
//
//
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}





	public internApp() throws FileNotFoundException {
	}

	@ForIntent("Default Welcome Intent")
	public ActionResponse defaultWelcome(ActionRequest request) throws ExecutionException, InterruptedException {
		ResponseBuilder rb = getResponseBuilder(request);

		Map<String, Object> data = rb.getConversationData();

		data.clear();

		List<String> suggestions = new ArrayList<String>();
		SimpleResponse simpleResponse = new SimpleResponse();

		simpleResponse.setTextToSpeech("안녕하세요, 원하시는 서비스를 말씀해주세요.")
				.setDisplayText("안녕하세요 원하시는 서비스를 말씀해주세요.")
		;

		rb
				.add("These are suggestion chips.")
				.addSuggestions(new String[] {"경기 일정", "다시보기", "실시간 중계", "전적 및 데이터 확인"})
				.add(
						new LinkOutSuggestion()
								.setDestinationName("Suggestion Link")
								.setUrl("https://assistant.google.com/"))
				.add("Which type of response would you like to see next?")
		;


		rb.add(simpleResponse);

		rb.addSuggestions(suggestions.toArray(new String[suggestions.size()]));
		return rb.build();
	}
	@ForIntent("TestLck_Schedule")
			public ActionResponse scheduleView(ActionRequest request) throws ExecutionException, InterruptedException {
			ResponseBuilder rb = getResponseBuilder(request);

			List<String> suggestions = new ArrayList<String>();
			SimpleResponse simpleResponse = new SimpleResponse();
			BasicCard basicCard = new BasicCard();

			simpleResponse.setTextToSpeech("어떤 일정을 알고싶으신가요?")
			.setDisplayText("어떤 일정을 알고싶으신가요?")
			;

		rb
				.addSuggestions(new String[] {"오늘", "이번주", "이번달"})
				.add(
						new LinkOutSuggestion()
								.setDestinationName("Suggestion Link")
								.setUrl("https://assistant.google.com/"))
				.add("예와 같은 간단한 단어 또는 특정 날짜를 말씀해주세요. ")
		;

			basicCard
			.setTitle("스케줄을 나타내는 제목입니다.")
			.setFormattedText("스케줄을 나타내는 텍스트입니다.")
			.setImage(new Image().setUrl("https://actions.o2o.kr/content/aiperson.gif")
					.setAccessibilityText("home"));

			rb.add(simpleResponse);
			rb.add(basicCard);

			return rb.build();
			}


	@ForIntent("Schedule_whatDay")
	public ActionResponse whatDay(ActionRequest request) throws ExecutionException, InterruptedException {
		ResponseBuilder rb = getResponseBuilder(request);
		String date = CommonUtil.makeSafeString(request.getParameter("whatday"));
		String team = CommonUtil.makeSafeString(request.getParameter("teamName"));
		SimpleResponse simpleResponse = new SimpleResponse();
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd"); //날짜 포멧 선언
		String nowDate = sd.format(new Date());	//현재시간



		//db연동해서 날짜 체크
		if(date.equals("어제")) {

		}else if(date.equals("오늘")){

		}else if(date.equals("내일")){

		}else if(date.equals("저번주")){

		}else if(date.equals("이번주")){

		}else if(date.equals("다음주")){

		}else if(date.equals("저번달")){

		}else if(date.equals("이번달")){

		}else if(date.equals("다음달")){

		}


		simpleResponse.setTextToSpeech(date + team +" 의 일정을 보여드릴게요.")
				.setDisplayText(date + team + " 의 일정을 보여드릴게요.")
		;

		rb.add(simpleResponse);


		return rb.build();
	}

	@ForIntent("TestLck_replay")
	public ActionResponse replayLck(ActionRequest request) throws ExecutionException, InterruptedException {
		ResponseBuilder rb = getResponseBuilder(request);

		List<String> suggestions = new ArrayList<String>();
		SimpleResponse simpleResponse = new SimpleResponse();
		BasicCard basicCard = new BasicCard();

		simpleResponse.setTextToSpeech("풀버전과 하이라이트 중 한가지를 선택해주세요.")
				.setDisplayText("풀버전과 하이라이트 중 한가지를 선택해주세요.")
		;

		rb
				.addSuggestions(new String[] {"풀버전", "하이라이트", })
				.add(
						new LinkOutSuggestion()
								.setDestinationName("Suggestion Link")
								.setUrl("https://assistant.google.com/"))
				.add("풀버전과 하이라이트 중 한가지를 골라주세요. ")
		;

		basicCard
				.setTitle("스케줄을 나타내는 제목입니다.")
				.setFormattedText("스케줄을 나타내는 텍스트입니다.")
				.setImage(new Image().setUrl("https://actions.o2o.kr/content/aiperson.gif")
						.setAccessibilityText("home"));

		rb.add(simpleResponse);
		rb.add(basicCard);

		return rb.build();
	}


}

