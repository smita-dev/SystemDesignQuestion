package com.tinyurl.tinyUrl.controller;

import com.tinyurl.tinyUrl.model.Url;
import com.tinyurl.tinyUrl.model.UrlDTO;
import com.tinyurl.tinyUrl.model.UrlErrorResponse;
import com.tinyurl.tinyUrl.service.UrlService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShorteningController {
    @Autowired
    UrlService urlService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateShortUrl(@RequestBody UrlDTO urlDTO){
        Url shortUrlToReturn= urlService.generateShortUrl(urlDTO);
        if(shortUrlToReturn!=null){
            return new ResponseEntity<Url>(shortUrlToReturn, HttpStatus.OK);
        }

        UrlErrorResponse urlErrorResponse=new UrlErrorResponse("404","There was error while processing your request. Please try again");
        return new ResponseEntity<UrlErrorResponse>(urlErrorResponse,HttpStatus.OK);
    }

    @GetMapping("/redirect")
    public ResponseEntity<?> redirectShortUrl(String shortUrl){
        if(StringUtils.isEmpty(shortUrl)){
            UrlErrorResponse urlErrorResponse=new UrlErrorResponse("400","Error in the requested url");
            return new ResponseEntity<UrlErrorResponse>(urlErrorResponse,HttpStatus.BAD_REQUEST);
        }

        if(urlService.getEncodedUrl(shortUrl)==null){
            UrlErrorResponse urlErrorResponse=new UrlErrorResponse("404","Url for given request not found");
            return new ResponseEntity<UrlErrorResponse>(urlErrorResponse,HttpStatus.NOT_FOUND);
        }

        return
    }

}
