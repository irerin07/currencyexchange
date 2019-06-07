package com.example.currencyexchange.service;

import com.example.currencyexchange.dto.CurrencyLayerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Service
public class CurrencyLayerAPIServiceImpl implements CurrencyLayerAPIService {
    @Value("${currencyLayer.accessKey}")
    private String accessKey;
    @Value("${currencyLayer.endPoint}")
    private String endPoint;
    @Value("${currencyLayer.source}")
    private String source;
    @Value("${currencyLayer.currencies}")
    private String currencies;
    @Value("${currencyLayer.refreshRate}")
    private int refreshRate;

    @Autowired
    WebClient webClient;
    private Mono<CurrencyLayerDto> currencyLayerDtoMono;
    private CurrencyLayerDto currencyLayerDto;

    @Override
    public CurrencyLayerDto getCurrencyRate() {
        if(checkIfLatestCurrency()==false) {
            currencyLayerDtoMono = webClient.get().uri("/live?access_key=" + accessKey + "&source=" + source + "&currencies=" + currencies)
                    .retrieve()
                    .bodyToMono(CurrencyLayerDto.class);
            currencyLayerDto = currencyLayerDtoMono.block();
            if (currencyLayerDto == null) {
                throw new RestClientException("환율정보를 불러오지 못했습니다.");
            } else if (!currencyLayerDto.isSuccess()) {
                throw new RestClientException("API 호출간 에러가 발생했습니다." + currencyLayerDto.getError().get("code") + " : " + currencyLayerDto.getError().get("info"));
            }
            return currencyLayerDto;
        }
        return currencyLayerDto;
    }

    public Boolean checkIfLatestCurrency(){
        boolean latest = false;
        if(currencyLayerDto == null){
            return false;
        }
        //처음에는 TimeUnit.MILLISECONDS.toSeconds를 사용해서 초단위로 변경하여 refreshRate와 비교를 했지만 Test단에서 자꾸 같은 결과를 다른 값이라고 하며 실패
        //구글링을 해보니 Milliseconds값을 테스트를 하면 작은 단위의 차이 때문에 테스트가 실패 할 가능성이 있다고 하며
        //조금 더 큰 단위로 변환하는것을 추천하기에 toMinutes 변경, refreshRate도 역시 분 단위로 변경
        long currentMin = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis());
        if(TimeUnit.SECONDS.toMinutes(currentMin - currencyLayerDto.getTimestamp()) > refreshRate){
            System.out.println("not latest! update required");
            latest = false;
        }
        if(TimeUnit.SECONDS.toMinutes(currentMin - currencyLayerDto.getTimestamp()) <= refreshRate){
            System.out.println("latest! update not required");
            latest = true;
        }
        return latest;
    }

}
