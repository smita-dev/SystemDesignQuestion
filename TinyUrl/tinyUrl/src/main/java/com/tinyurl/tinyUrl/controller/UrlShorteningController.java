package com.tinyurl.tinyUrl.controller;

import com.tinyurl.tinyUrl.model.Url;
import com.tinyurl.tinyUrl.model.UrlDTO;
import com.tinyurl.tinyUrl.model.UrlErrorResponse;
import com.tinyurl.tinyUrl.service.UrlService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

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

    @GetMapping("/{shortUrl}")
    public ResponseEntity<?> redirectShortUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        if(StringUtils.isEmpty(shortUrl)){
            UrlErrorResponse urlErrorResponse=new UrlErrorResponse("400","Error in the requested url");
            return new ResponseEntity<UrlErrorResponse>(urlErrorResponse,HttpStatus.BAD_REQUEST);
        }

        Url urlInfo=urlService.getEncodedUrl(shortUrl);
        if(urlInfo==null){
            UrlErrorResponse urlErrorResponse=new UrlErrorResponse("404","Url is not present or might have expired.");
            return new ResponseEntity<UrlErrorResponse>(urlErrorResponse,HttpStatus.NOT_FOUND);
        }

        if(urlInfo.getExpirationDate().isBefore(LocalDateTime.now())){
            urlService.deleteShortUrl(urlInfo);
            UrlErrorResponse urlErrorResponse=new UrlErrorResponse("404","Url is expired. Please try generating new one.");
            return new ResponseEntity<UrlErrorResponse>(urlErrorResponse,HttpStatus.NOT_FOUND);
        }

        response.sendRedirect(urlInfo.getOriginalurl());
        return null;
    }

}
