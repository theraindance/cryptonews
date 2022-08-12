package com.ssfone.cryptonews.Services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ssfone.cryptonews.Model.Articles;

@Service
public class NewsService {

    @Autowired
    private RestTemplate template = new RestTemplate();
    // private static String URL = "https://min-api.cryptocompare.com/data/v2/news/?lang=EN";
    public String getArticles(){
        RestTemplate template = new RestTemplate();
        return getArticles();
    }

    public static void save(Articles jtj) {
    }

    // public Map<String, String> getListUrl() {
    //     RestTemplate template = new RestTemplate();
    //     // String lsCurrencyUrl = currencyComponentDynamicBuilder(LS_CURRENCY,
    //     // lsGeoCodeMapBuilder());

    //     //ResponseEntity<String> resp = template.getForEntity(URL, String.class);

    //     //Map<String, String> listUrl = CurrencyCode.lsOfCountryCode(resp.getBody());
    //     //logger.info("Retrieve list of country code: {}", lsOfGeoCode);
    //     return listUrl;
    // }

    // }
    
}
