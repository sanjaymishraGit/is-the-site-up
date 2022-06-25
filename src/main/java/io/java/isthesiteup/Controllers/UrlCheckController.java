package io.java.isthesiteup.Controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {

    
    private final String SITEDOWN = "Site is down!";
    private final String SITEUP= "Site is up!";
    private final String INCORRECTURL="URL is Incorrect";
    

    
    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url){
    
        String returnMessage="";
        URL urlObj;
        try {
            urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection(); 
            connection.setRequestMethod("GET");
            connection.connect();
            var responseCode = connection.getResponseCode();
            int resposeCodeCategory = responseCode/100;
            if(resposeCodeCategory!=2 || resposeCodeCategory!= 3){
                returnMessage = SITEDOWN;
            }
            returnMessage = SITEUP;
        } 
        
        catch (MalformedURLException e) {
            returnMessage =INCORRECTURL;
            
        } 
        catch (IOException e) {
            returnMessage =SITEDOWN;
        }
      
        

        return returnMessage;

    }
}
