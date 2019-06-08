# currencyexchange
currency calculator using currencyLayer API  
currencylayer api를 사용해 구현한 간단한 환율 계산 프로그램입니다.
![Imgur](http://i.imgur.com/FpbRQGw.png)
![Imgur](http://i.imgur.com/ags9igM.png)
![Imgur](http://i.imgur.com/77lno5l.png)


# 사용 기술
* Java 8
* SpringBoot 2.1.5
* Thymeleaf
* JavaScript
* jQuery

# 구현 요구사항
* 송금국가는 미국으로 고정입니다. 통화는 미국달러(USD)입니다.
* 수취국가는 한국, 일본, 필리핀 세 군데 중 하나를 select box로 선택합니다. 각각 통화는 KRW, JPY, PHP 입니다.
* 수취국가를 선택하면 아래 환율이 바뀌어나타나야 합니다. 환율은 1 USD 기준으로 각각 KRW, JPY, PHP의 대응 금액입니다.
* 송금액을 USD로 입력하고 Submit을 누르면 아래 다음과 같이 수취금액이 KRW, JPY, PHP 중 하나로 계산되어서 나와야 합니다.
* 환율과 수취금액은 소숫점 2째자리까지, 3자리 이상 되면 콤마를 가운데 찍어 보여줍니다. 예를 들어 1234라면 1,234.00으로 나타냅니다.
* 환율정보는 https://currencylayer.com/ 의 무료 서비스를 이용해서 실시간으로 가져와야 합니다. 웹 서버가 시작될 때 한번 가져와서 계속 사용해도 되고, 매번 새로운 환율 정보를 가져와도 됩니다.
* 새로운 무료 계정을 등록해서 API 키를 받아서 사용하면 됩니다.
* 환율을 미리 계산해서 html/javascript 안에 넣어두고 수취국가를 변경할 때마다 이를 자바스크립트로 바로 가져와서 보여줘도 좋고,
* 혹은 매번 수취국가를 선택/변경할 때마다 API로 서버에 요청을 해서 환율정보를 가져오게 해도 좋습니다.
* Submit을 누르면 선택된 수취국가와 그 환율, 그리고 송금액을 가지고 수취금액을 계산해서 하단에 보여주면 됩니다. API를 이용해서 서버에서 계산해서 뿌려도 되고 자바스크립트로 미리 가져온 환율을 계산해서 수취금액을 보여줘도 되고 Submit 버튼으로 폼을 submit해서 화면을 새로 그려도 됩니다.
* 수취금액을 입력하지 않거나, 0보다 작은 금액이거나 10,000 USD보다 큰 금액, 혹은 바른 숫자가 아니라면 “송금액이 바르지 않습니다"라는 에러 메시지를 보여줍니다. 메시지는 팝업, 혹은 하단에 빨간 글씨로 나타나면 됩니다.

# 구현 기능
* CurrencyLayerRESTController
  * getExchangeRate
  * getExchangedAmount
* GetCurrencyRateScheduler
  * updateCurrencyRates
* CurrencyCalcService
  *  getExchangeRate
* CurrencyLayerAPIService
  *  getCurrencyRate

# 설계
* 

# 어려웠던 부분
* CurrencyLayerAPIService  
  * Webclient를 통해 Mono형태로 데이터를 가지고 왔지만 이 부분을 어떻게 처리해야 하나 굉장히 고민을 많이 했습니다.
기존 TwelveBooks 프로젝트에서는 Mono형태로 프런트로 값을 넘겨 준 다음에 처리했지만 
이번 미니 프로젝트의 경우에는 환율 계산을 하고 소수점까지  결과값을 넘겨주고 싶어서 Mono형태를 Dto에 맞게 푸는 방법을 찾느라 검색을 많이 했습니다.
map, subscribe, doOnNext등 여러가지 방법들을 사용해봤지만 원하는 결과가 나오지 않아 고생을 좀 했습니다.

* Git
  * 커밋과 푸쉬를 실수 한 부분이 있어서 로그/히스토리가 꼬여버렸습니다. $git reset --hard 기능을 사용하여 되돌려보려 했지만 지정한 시점 이후의 커밋 이력이 여전히 남아있는것이 보입니다. 지금은 문제가 없어 보이지만 무슨 문제가 발생할지 아직 알 수 없어서 불안합니다. 



# 개선점
* 스케줄러를 쓸것인지 말것인지 고민을 해봐야 할 것 같다. 스케줄러를 써서 매시간 환율정보를 업데이트 해주면 항상 최신 정보를 유지할 수 있겠지만 API 호출이 너무 자주 일어나게된다.
* 현재 사용하고 있는 정규표현식은 문자와 숫자의 조합을 잡아내지 못하고 있다. (eg. 1q2w) 이 부분은 수정이 필요할것 같다.

