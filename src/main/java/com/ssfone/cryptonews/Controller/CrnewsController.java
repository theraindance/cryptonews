package com.ssfone.cryptonews.Controller;

import java.net.HttpURLConnection;
import java.util.Scanner;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ssfone.cryptonews.Model.Articles;

@RestController
public class CrnewsController {
    RestTemplate template = new RestTemplate();
    @GetMapping("/")
    public String getHome(Model model, @ModelAttribute Articles currObject){
        
        String url = "https://min-api.cryptocompare.com/data/v2/news/?lang=EN";
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST"); 
        conn.connect(); 
        int responsecode = conn.getResponseCode();

        String inline;
        if(responsecode != 200)
                throw new RuntimeException();
        else
        {
            Scanner sc = new Scanner(url.openStream());
                while(sc.hasNext())
                {
                    inline+=sc.nextLine();
                }
                    
                sc.close();
           
        }
            JSONParser parse = new JSONParser(); 
            JSONObject jobj = (JSONObject)parse.parse(inline); 



        return url;
    }
    
    @PostMapping("/text")
        public String getCurr(Model model, @ModelAttribute Articles currObject){
        
        
        //? is start of request parameter, get request
        //& is for seperation of different request parameter
        String url = "https://min-api.cryptocompare.com/data/v2/news/?lang=EN";
        

        String apikey= " ";
        // basically like POSTMAN where you input URL+header, before press send
        RequestEntity<Void> request = RequestEntity.get(url).header("apikey", apikey)
        .accept(MediaType.APPLICATION_JSON).build();
        //line 47 is POSTMAN's send function, get back JSON object, the class we referring to must be your jsontojava file
        ResponseEntity<Articles> response = template.exchange(request, Articles.class);

        //create new object using filename
        //only get what you need from API JSON to put inside jtj
        Articles jtj = response.getBody();
       // service.save(jtj);

        // @ModelAttribute from form
        // each data input need their own model.addAttribute(from server pass in data to exchange website), here you need 8
        // in exchange.html, to gets its result from the model.addAttribute

        
        model.addAttribute("id",jtj.getId());

        model.addAttribute("published_on",jtj.getPublished_on());

        model.addAttribute("title",jtj.getTitle());
        
        model.addAttribute("url",jtj.getUrl());
        
        model.addAttribute("imageurl",jtj.getImageurl());

        model.addAttribute("body",jtj.getBody());

        model.addAttribute("tags",jtj.getTags());
        
        model.addAttribute("categories",jtj.getCategories());

        return "getnews";

    }


}
