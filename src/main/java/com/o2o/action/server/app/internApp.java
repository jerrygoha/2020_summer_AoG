package com.o2o.action.server.app;

import com.google.actions.api.*;
import com.google.actions.api.response.ResponseBuilder;
import com.google.api.services.actions_fulfillment.v2.model.*;
import com.o2o.action.server.util.CommonUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class internApp extends DialogflowApp {

	dateCall dtCall = new dateCall();
	private String mediatype = null;
	private final String[] teamName = {"T1", "GEN", "DRX", "DWG", "KT", "AF", "SP", "HLE", "DYN", "SB"};
	private final int[][] schedule = dtCall.schedule();
	private final String[] dayTable = dtCall.date();

	@ForIntent("Default Welcome Intent")
	public ActionResponse defaultWelcome(ActionRequest request) throws ExecutionException, InterruptedException {
		ResponseBuilder rb = getResponseBuilder(request);

		Map<String, Object> data = rb.getConversationData();

		data.clear();

		List<String> suggestions = new ArrayList<String>();
		SimpleResponse simpleResponse = new SimpleResponse();

		simpleResponse.setTextToSpeech("안녕하세요, 원하시는 서비스를 말씀해주세요.")
				.setDisplayText("안녕하세요 원하시는 서비스를 말씀해주세요.  팀이름테스트" + teamName[1] + "스케줄테스트" + Integer.toString(schedule[1][1])+ "날짜테스" +dayTable[1])
		;

		//테스트
		//simpleResponse.setDisplayText(teamName[1]);
		//simpleResponse.setDisplayText(Integer.toString(schedule[1][1]));
		//simpleResponse.setDisplayText(dayTable[1]);
		//테스트

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

	//경기 일정

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
				calcDay = dtCall.calcDate(cal.getTime());
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
				calcDay = dtCall.calcDate(cal.getTime());
				cal.setTime(current);
				break;
			}
			case "저번주": {
				cal.add(Calendar.WEEK_OF_MONTH, -1);
				calcDay = dtCall.calcDate(cal.getTime());
				cal.setTime(current);
				break;
			}
			case "다음주": {
				cal.add(Calendar.WEEK_OF_MONTH, 1);
				calcDay = dtCall.calcDate(cal.getTime());
				cal.setTime(current);
				break;
			}
			case "저번달": {
				cal.add(Calendar.MONTH, -1);
				calcDay = dtCall.calcDate(cal.getTime());
				cal.setTime(current);
				break;
			}
			case "다음달": {
				cal.add(Calendar.MONTH, 1);
				calcDay = dtCall.calcDate(cal.getTime());
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

	//다시보기

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


	@ForIntent("inputDate_replay")
	public ActionResponse replayDate(ActionRequest request) throws ExecutionException, InterruptedException {
		ResponseBuilder rb = getResponseBuilder(request);
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");

		List<String> suggestions = new ArrayList<String>();
		SimpleResponse simpleResponse = new SimpleResponse();
		BasicCard basicCard = new BasicCard();

		String replaydate = CommonUtil.makeSafeString(request.getParameter("date")).substring(0,10).replace("-", "").replace("2021", "2020");
		//2021-07-14T12:00:00+09:00 형태로 받아온다.
		//위 로직 거치면 20200714 뱉



		boolean isthere = false; //기간 유효성 판단

		int count = 0;
		for(int i = 0; i< dayTable.length; i++){ //날짜테이블

			if(dayTable[i].equals(replaydate)){	//날짜찾음
				for(int j = 0; j < teamName.length; j++){
					count =+ schedule[i][j];
				}
				if(count != 0 ){	//해당 날짜에 경기 있을때
					isthere = true;
					if (mediatype.equals("highlight")) { 	//하이라이트
						rb
								.add(
										new BasicCard()
												.setTitle("하이라이트 서비스로 연결합니다.")
												.setSubtitle("유튜브 하이라이트로 연결합니다.")
												.setButtons(
														new ArrayList<Button>(
																Arrays.asList(
																		new Button()
																				.setTitle("보러 가기")
																				.setOpenUrlAction(
																						new OpenUrlAction().setUrl("https://www.youtube.com/results?search_query=우리은행++lck+하이라이트+" + replaydate))))))
						;
						break;
					}else{
						rb
								.add(
										new BasicCard()
												.setTitle("풀버전 다시보기 서비스로 연결합니다.")
												.setSubtitle("유튜브 풀버전으로 연결합니다.")
												.setButtons(
														new ArrayList<Button>(
																Arrays.asList(
																		new Button()
																				.setTitle("보러 가기")
																				.setOpenUrlAction(
																						new OpenUrlAction().setUrl("https://www.youtube.com/results?search_query=lck+full+" + replaydate))))))
						;
						break;
					}

				}
			}

		}
		if( count == 0){
			simpleResponse.setTextToSpeech("해당 일자는 경기가 없습니다. 다시한번 날짜를 말씀해주세요.")
					.setDisplayText("해당 일자는 경기가 없습니다. 다시한번 날짜를 말씀해주세요.")
			;
			rb.removeContext("inputDate_replay");
		}

		simpleResponse.setTextToSpeech(replaydate + " 경기를 " +  mediatype + " 으로 보고싶다는 말씀이시죠? ")
				.setDisplayText(replaydate + " 경기를 " +  mediatype + " 으로 보고싶다는 말씀이시죠? ")
		;


		rb.add(simpleResponse);


		return rb.build();
	}

	//실시간

	@ForIntent("TestLck_live")
	public ActionResponse testLive(ActionRequest request) throws ExecutionException, InterruptedException {
		ResponseBuilder rb = getResponseBuilder(request);

		List<String> suggestions = new ArrayList<String>();
		SimpleResponse simpleResponse = new SimpleResponse();

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
		rb.addSuggestions(suggestions.toArray(new String[suggestions.size()]));

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
						.add(
								new BasicCard()
										.setTitle("네이버 스포츠 실시간 연결")
										.setSubtitle("네이버 스포츠 lck 실시간 중계 페이지로 이동합니다.")
										.setButtons(
												new ArrayList<Button>(
														Arrays.asList(
																new Button()
																		.setTitle("방송 보러 가기")
																		.setOpenUrlAction(
																				new OpenUrlAction().setUrl("https://m.sports.naver.com/esports/index.nhn"))))));
				break;
			case "twitch" :		//트위치 실시간으로 연결
				rb
						.add(
								new BasicCard()
										.setTitle("트위치 실시간 연결")
										.setSubtitle("트위치 lck 실시간 중계 페이지로 이동합니다.")
										.setButtons(
												new ArrayList<Button>(
														Arrays.asList(
																new Button()
																		.setTitle("방송 보러 가")
																		.setOpenUrlAction(
																				new OpenUrlAction().setUrl("https://www.twitch.tv/lck_korea"))))));
				break;
		}



		rb.add(simpleResponse);


		return rb.build();
	}

}

