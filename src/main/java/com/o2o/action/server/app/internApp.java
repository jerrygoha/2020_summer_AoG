package com.o2o.action.server.app;

import com.google.actions.api.*;
import com.google.actions.api.Capability;
import com.google.actions.api.response.ResponseBuilder;
import com.google.actions.api.response.helperintent.SelectionCarousel;
import com.google.api.services.actions_fulfillment.v2.model.*;
import com.google.gson.*;
import com.o2o.action.server.util.CommonUtil;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;

public class internApp extends DialogflowApp {

	private String mediatype = null;

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

				.addSuggestions(new String[] {"경기 일정", "다시보기", "실시간 중계", "전적 및 데이터 확인"})
				.add(
						new LinkOutSuggestion()
								.setDestinationName("Suggestion Link")
								.setUrl("https://assistant.google.com/"))
				.add("어떤 서비스를 이용하고싶으신가요?")
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
			rb.add(basicCard);

			rb.add(simpleResponse);


			return rb.build();
			}


	@ForIntent("whatDay")
	public ActionResponse whatDay(ActionRequest request) throws ExecutionException, InterruptedException, ParseException {
		ResponseBuilder rb = getResponseBuilder(request);
		String date = CommonUtil.makeSafeString(request.getParameter("whatday"));
		String team = CommonUtil.makeSafeString(request.getParameter("teamName"));

		SimpleResponse simpleResponse = new SimpleResponse();
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd"); //날짜 포멧 선언
		String nowDate = sd.format(new Date());	//현재시간
		Date current = new Date();
		String currentDateSt = sd.format(current);
		String calcDay;
		int currentDateInt = Integer.parseInt(currentDateSt);

		Calendar cal = Calendar.getInstance();
		cal.setTime(current);

		//날짜 체크
		switch (date) {
			case "어제": {
				cal.add(Calendar.DATE, -1);
				dateCall yesterday = new dateCall(cal.getTime());
				calcDay = yesterday.calcDate();
				cal.setTime(current);
				break;
			}
			case "오늘":{
				calcDay = "today";
				break;
			}
			case "이번주":{
				calcDay = "thisweek";
				break;
			}
			case "이번달": {
				calcDay = "thismonth";
				break;
			}
			case "내일": {
				cal.add(Calendar.DATE, 1);
				dateCall tomorrow = new dateCall(cal.getTime());
				calcDay = tomorrow.calcDate();
				cal.setTime(current);
				break;
			}
			case "저번주": {
				cal.add(Calendar.WEEK_OF_MONTH, -1);
				dateCall lastweek = new dateCall(cal.getTime());
				calcDay = lastweek.calcDate();
				cal.setTime(current);
				break;
			}
			case "다음주": {
				cal.add(Calendar.WEEK_OF_MONTH, 1);
				dateCall nextweek = new dateCall(cal.getTime());
				calcDay = nextweek.calcDate();
				cal.setTime(current);
				break;
			}
			case "저번달": {
				cal.add(Calendar.MONTH, -1);
				dateCall lastmonth = new dateCall(cal.getTime());
				calcDay = lastmonth.calcDate();
				cal.setTime(current);
				break;
			}
			case "다음달": {
				cal.add(Calendar.MONTH, 1);
				dateCall nextmonth = new dateCall(cal.getTime());
				calcDay = nextmonth.calcDate();
				cal.setTime(current);
				break;
			}
			default:
				throw new IllegalStateException("Unexpected value: " + date);
		}




		simpleResponse.setTextToSpeech(calcDay + team +" 의 일정을 보여드릴게요.")
				.setDisplayText(calcDay + team + " 의 일정을 보여드릴게요.")
		;

		rb.add(simpleResponse);


		return rb.build();
	}

	@ForIntent("selectFullHighlight")
	public ActionResponse selectFH(ActionRequest request) throws ExecutionException, InterruptedException {
		ResponseBuilder rb = getResponseBuilder(request);

		List<String> suggestions = new ArrayList<String>();
		SimpleResponse simpleResponse = new SimpleResponse();
		BasicCard basicCard = new BasicCard();
		mediatype = CommonUtil.makeSafeString(request.getParameter("fullorhighlight"));

		simpleResponse.setTextToSpeech("그럼 이제 보고싶은 날짜를 말씀해주시겠어요?")
				.setDisplayText("그럼 이제 보고싶은 날짜를 말씀해주시겠어요?")
		;


		rb.add(simpleResponse);


		return rb.build();
	}

	@ForIntent("selectYouTubeOrTwitch")
	public ActionResponse selectYT(ActionRequest request) throws ExecutionException, InterruptedException {
		ResponseBuilder rb = getResponseBuilder(request);

		SimpleResponse simpleResponse = new SimpleResponse();
		String selectYT = CommonUtil.makeSafeString(request.getParameter("selectyt"));

		switch (selectYT){
			case "naver" :	//네이버 실시간 연결
				rb
						.add("naver sports")
						.add(
								new BasicCard()
										.setTitle("네이버 스포츠 실시간 연결")
										.setSubtitle("네이버 스포츠 lck 실시간 중계 페이지로 이동합니다.")
										.setImage(
												new Image()
														.setUrl(
																"https://storage.googleapis.com/actionsresources/logo_assistant_2x_64dp.png")
														.setAccessibilityText("Image alternate text"))
										.setImageDisplayOptions("CROPPED")
										.setButtons(
												new ArrayList<Button>(
														Arrays.asList(
																new Button()
																		.setTitle("방송 보러 가기")
																		.setOpenUrlAction(
																				new OpenUrlAction().setUrl("https://sports.news.naver.com/esports/index.nhn"))))))
						.add("Which response would you like to see next?");
				break;
			case "twitch" :		//트위치 실시간으로 연결
				rb
						.add("twitch")
						.add(
								new BasicCard()
										.setTitle("트위치 실시간 연결")
										.setSubtitle("트위치 lck 실시간 중계 페이지로 이동합니다.")
										.setImage(
												new Image()
														.setUrl(
																"https://storage.googleapis.com/actionsresources/logo_assistant_2x_64dp.png")
														.setAccessibilityText("Image alternate text"))
										.setImageDisplayOptions("CROPPED")
										.setButtons(
												new ArrayList<Button>(
														Arrays.asList(
																new Button()
																		.setTitle("방송 보러 가")
																		.setOpenUrlAction(
																				new OpenUrlAction().setUrl("https://www.twitch.tv/lck_korea"))))))
						.add("Which response would you like to see next?");
				break;
		}

		simpleResponse.setTextToSpeech("네이버와 트위치 중 실시간 중계를 시청하실 플랫폼을 선택해주세요.")
				.setDisplayText("네이버와 트위치 중 실시간 중계를 시청하실 플랫폼을 선택해주세요.")
		;

		rb
				.addSuggestions(new String[] {"네이버", "트위치", })
				.add(
						new LinkOutSuggestion()
								.setDestinationName("Suggestion Link")
								.setUrl("https://assistant.google.com/"))
				.add("풀버전과 하이라이트 중 한가지를 골라주세요. ")
		;



		rb.add(simpleResponse);


		return rb.build();
	}

	@ForIntent("inputDate_replay")
	public ActionResponse replayDate(ActionRequest request) throws ExecutionException, InterruptedException {
		ResponseBuilder rb = getResponseBuilder(request);
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");

		List<String> suggestions = new ArrayList<String>();
		SimpleResponse simpleResponse = new SimpleResponse();
		BasicCard basicCard = new BasicCard();

		String replaydate = CommonUtil.makeSafeString(request.getParameter("date"));
		//2021-07-14T12:00:00+09:00 형태로 받아온다.


		simpleResponse.setTextToSpeech(replaydate + " 경기를 보고싶다는 말씀이시죠? ")
				.setDisplayText(replaydate + " 경기를 보고싶다는 말씀이시죠? ")
		;


		rb.add(simpleResponse);


		return rb.build();
	}

}

