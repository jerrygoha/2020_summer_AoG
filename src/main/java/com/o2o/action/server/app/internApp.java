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
	private String mediatype = "def";
	private final String[] teamName = {"T1", "GEN", "DRX", "DWG", "KT", "AF", "SP", "HLE", "DYN", "SB"};
	private final int[][] schedule = dtCall.schedule();
	private final int[] dayTable = dtCall.date();
	private String gameSchedule = "";

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
				.add("안녕하세요! 롤 E-sports 입니다! 아래 키워드를 참고하여 말해보세요! ")
				.add(
						new BasicCard()
								.setImage(
										new Image()
												.setUrl("https://actions.o2o.kr/devsvr5/image/tenor.gif")
												.setAccessibilityText("Image alternate text"))
								.setImageDisplayOptions("CROPPED"))
				.add("아래의 간단한 키워드를 참고해주세요.");


		rb

				.addSuggestions(new String[] {"경기 일정", "다시보기", "실시간 중계", "전적 및 데이터 확인"})
				.add(
						new LinkOutSuggestion()
								.setDestinationName("Suggestion Link")
								.setUrl("https://assistant.google.com/"))
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


			simpleResponse.setTextToSpeech("어떤 일정을 알고싶으신가요?")
			.setDisplayText("<하루전 / 당일 / 다음날 / 저번주 / 이번주 / 다음주 / 저번달 / 이번달 / 다음달 >  \n의 조회가 가능합니다. ")
			;

		rb
				.addSuggestions(new String[] {"오늘", "이번주", "이번달"})
				.add(
						new LinkOutSuggestion()
								.setDestinationName("Suggestion Link")
								.setUrl("https://assistant.google.com/"))
				.add("현재는 간단한 일정만 가능합니다.")
		;



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
		Date current = new Date();
		BasicCard basicCard = new BasicCard();
		String currentDateSt = sd.format(current);
		String calcDay;

		Calendar cal = Calendar.getInstance();
		cal.setTime(current);

		//날짜 체크
		switch (date) {
			case "어제": {
				cal.add(Calendar.DATE, -1);
				calcDay = dtCall.calcDate(cal.getTime());
				gameSchedule = dtCall.gameDaySchedule(cal.getTime());
				cal.setTime(current);
				break;
			}
			case "오늘":{
				cal.add(Calendar.DATE, 0);
				calcDay = "today";
				gameSchedule = dtCall.gameDaySchedule(cal.getTime());
				break;
			}
			case "이번주":{
				cal.add(Calendar.WEEK_OF_MONTH, 0);
				calcDay = "thisweek";
				gameSchedule = dtCall.gameWeekSchedule(calcDay, cal.getTime());
				break;
			}
			case "이번달": {
				cal.add(Calendar.MONTH, 0);
				calcDay = "thismonth";
				break;
			}
			case "내일": {
				cal.add(Calendar.DATE, 1);
				calcDay = dtCall.calcDate(cal.getTime());
				gameSchedule = dtCall.gameDaySchedule(cal.getTime());
				cal.setTime(current);
				break;
			}
			case "저번주": {
				cal.add(Calendar.WEEK_OF_MONTH, -1);
				calcDay = dtCall.calcDate(cal.getTime());
				gameSchedule = dtCall.gameWeekSchedule(calcDay, cal.getTime());
				cal.setTime(current);
				break;
			}
			case "다음주": {
				cal.add(Calendar.WEEK_OF_MONTH, 1);
				calcDay = dtCall.calcDate(cal.getTime());
				gameSchedule = dtCall.gameWeekSchedule(calcDay, cal.getTime());
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
		String juneImgUrl = "https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/blt9e79543d3d90306a/5f0be7caf394777f87099c0d/02.png";
		String julyImgUrl = "https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/blt4838ff981fc2ea49/5f0be7da600d867e81bca2f8/03.png";
		String augustImgUrl = "https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/bltf5db6089a168b384/5f0be7e48b0e59084c9f7792/04.png";

		if(date.equals("저번달")){
			rb
					.add("경기일정을 알려드리겠습니다.")
					.add(
							new BasicCard()
									.setTitle(date + " 경기일정 ")
									.setImage(new Image().setUrl(juneImgUrl)
											.setAccessibilityText(date + "경기일정"))
											.setImageDisplayOptions("CROPPED")
									.setButtons(
											new ArrayList<Button>(
													Arrays.asList(
															new Button()
																	.setTitle(date +" 일정 크게보기")
																	.setOpenUrlAction(
																			new OpenUrlAction().setUrl(juneImgUrl))))))
					.add("다른날짜,기능 호출 또는 대화를 종료할 수 있습니다.")
			;
			rb.removeContext("whatDay");
		}else if(date.equals("이번달")){
			rb
					.add("경기일정을 알려드리겠습니다.")
					.add(
							new BasicCard()
									.setTitle(date + " 경기일정 ")
									.setImage(new Image().setUrl(julyImgUrl)
											.setAccessibilityText(date + "경기일정"))
											.setImageDisplayOptions("CROPPED")
									.setButtons(
											new ArrayList<Button>(
													Arrays.asList(
															new Button()
																	.setTitle(date +" 일정 크게보기")
																	.setOpenUrlAction(
																			new OpenUrlAction().setUrl(julyImgUrl))))))
					.add("다른날짜,기능 호출 또는 대화를 종료할 수 있습니다.")
			;
			rb.removeContext("whatDay");
		}else if(date.equals("다음달")){
			rb
					.add("경기일정을 알려드리겠습니다.")
					.add(
							new BasicCard()
									.setTitle(date + " 경기일정 ")
									.setImage(new Image().setUrl(augustImgUrl)
											.setAccessibilityText(date + "경기일"))
											.setImageDisplayOptions("CROPPED")
									.setButtons(
											new ArrayList<Button>(
													Arrays.asList(
															new Button()
																	.setTitle(date +" 일정 크게보기")
																	.setOpenUrlAction(
																			new OpenUrlAction().setUrl(augustImgUrl))))))
					.add("다른날짜,기능 호출 또는 대화를 종료할 수 있습니다.")
			;
			rb.removeContext("whatDay");
		}else{
			rb
					.add("경기일정을 알려드리겠습니다.")
					.add(
							new BasicCard()
									.setTitle(date + " 경기일정 ")
									.setFormattedText(gameSchedule)
									.setButtons(
											new ArrayList<Button>(
													Arrays.asList(
															new Button()
																	.setTitle("전체 일정 보러가기")
																	.setOpenUrlAction(
																			new OpenUrlAction().setUrl("https://kr.leagueoflegends.com/ko-kr/news/esports/2020-lck-summer-information/"))))))
					.add("다른날짜,기능 호출 또는 대화를 종료할 수 있습니다.")
			;
			rb.removeContext("whatDay");
		}



		rb.add(simpleResponse);


		return rb.build();
	}

	//다시보기

	@ForIntent("select_replay")
	public ActionResponse selectFH(ActionRequest request) throws ExecutionException, InterruptedException {
		ResponseBuilder rb = getResponseBuilder(request);
		SimpleResponse simpleResponse = new SimpleResponse();

		simpleResponse.setTextToSpeech("그럼 이제 보고싶은 날짜를 말씀해주시겠어요?")
				.setDisplayText("시청을 원하시는 날짜를 입력해주세요.")
		;


		rb.add(simpleResponse);


		return rb.build();
	}


	@ForIntent("inputDate_replay")
	public ActionResponse replayDate(ActionRequest request) throws ExecutionException, InterruptedException {

		ResponseBuilder rb = getResponseBuilder(request);
		SimpleResponse simpleResponse = new SimpleResponse();

		String repldate = CommonUtil.makeSafeString(request.getParameter("date-time")).substring(0,10).replaceAll("-", "").replace("2021", "2020");
		//2020-07-12T12:00:00+09:00
		//20200712
		String inmonth = repldate.substring(4,6);
		String inday = repldate.substring(6);
		int replaydateInt = Integer.parseInt(repldate);

		int count = dtCall.findSchedule(replaydateInt);
				if(count != 0){	//해당 날짜에 경기 있을때

					rb
							.add("풀버전과 하이라이트 중 한가지를 골라주세요.")
							.add(
									new CarouselBrowse()
											.setItems(
													new ArrayList<CarouselBrowseItem>(
															Arrays.asList(
																	new CarouselBrowseItem()
																			.setTitle( repldate +" 하이라이트 보기")
																			.setDescription("LCK - 하이라이트")
																			.setOpenUrlAction(new OpenUrlAction().setUrl("https://www.youtube.com/results?search_query=우리은행++lck+h/l+" + inmonth+"."+inday))
																			.setImage(
																					new Image()
																							.setUrl(
																									"https://storage.googleapis.com/actionsresources/logo_assistant_2x_64dp.png")
																							.setAccessibilityText("Image alternate text"))
																			.setFooter(""),
																	new CarouselBrowseItem()
																			.setTitle( repldate + " 풀버전 보기")
																			.setDescription("LCK - 풀버전")
																			.setOpenUrlAction(new OpenUrlAction().setUrl("https://www.youtube.com/results?search_query=lck+full+" + inmonth+"."+inday))
																			.setImage(
																					new Image()
																							.setUrl(
																									"https://storage.googleapis.com/actionsresources/logo_assistant_2x_64dp.png")
																							.setAccessibilityText("Image alternate text"))
																			.setFooter(""))))).add("누르면 연결됩니다.");
				}else if(count == 0 ){
					simpleResponse.setTextToSpeech("해당 일자는 경기가 없습니다. 다시한번 날짜를 말씀해주세요.")
						.setDisplayText("해당 일자는 경기가 없습니다. 다시한번 날짜를 말씀해주세요.")
					;
				}
//		simpleResponse.setTextToSpeech("해당 일자는 경기가 없습니다. 다시한번 날짜를 말씀해주세요.")
//				.setDisplayText("날짜 " + repldate + "카운트 "+count+ "해당 일자는 경기가 없습니다. 다시한번 날짜를 말씀해주세요.")
//		;

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

		;



		rb.add(simpleResponse);
		rb.addSuggestions(suggestions.toArray(new String[suggestions.size()]));

		return rb.build();
	}


	@ForIntent("selectNaverOrTwitch")
	public ActionResponse selectYT(ActionRequest request) throws ExecutionException, InterruptedException {
		ResponseBuilder rb = getResponseBuilder(request);

		SimpleResponse simpleResponse = new SimpleResponse();
		String selectYT = CommonUtil.makeSafeString(request.getParameter("selectyt"));
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		Date date =  new Date();
		int replaydateInt = Integer.parseInt(sd.format(date));

		int count = dtCall.findSchedule(replaydateInt);

		if(count !=0){
			switch (selectYT){
				case "네이버" : //네이버 실시간 연결
					rb
							.add("네이버로 연결합니다.")
							.add(
									new BasicCard()
											.setTitle("LCK 중계 - naver")
											.setButtons(
													new ArrayList<Button>(
															Arrays.asList(
																	new Button()
																			.setTitle("중계 보러가기")
																			.setOpenUrlAction(
																					new OpenUrlAction().setUrl("https://m.sports.naver.com/esports/index.nhn"))))))
							.add("누르면 연결됩니다.")
					;
					break;
				case "트위치" :		//트위치 실시간으로 연결
					rb
							.add("트위치로 연결합니다.")
							.add(
									new BasicCard()
											.setTitle("LCK 중계 - twitch")
											.setButtons(
													new ArrayList<Button>(
															Arrays.asList(
																	new Button()
																			.setTitle("중계 보러가기")
																			.setOpenUrlAction(
																					new OpenUrlAction().setUrl("https://www.twitch.tv/lck_korea"))))))
							.add("누르면 연결됩니다.")
					;

					break;
			}
		}else if(count == 0){
			simpleResponse.setTextToSpeech("오늘은 경기가 없는 날입니다.")
					.setDisplayText("오늘은 경기가 없는 날입니다. 일정 확인 을 통해 일정을 확인해주세요.")
			;
		}


		rb.add(simpleResponse);


		return rb.build();
	}

}

