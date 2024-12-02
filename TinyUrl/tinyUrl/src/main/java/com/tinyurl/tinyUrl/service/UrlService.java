package com.tinyurl.tinyUrl.service;

import com.google.common.hash.Hashing;
import com.tinyurl.tinyUrl.model.Url;
import com.tinyurl.tinyUrl.model.UrlDTO;
import com.tinyurl.tinyUrl.repository.UrlRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class UrlService {
    @Autowired
    UrlRepository urlRepository;

    UrlService(UrlRepository urlRepository){
        this.urlRepository=urlRepository;
    }
    public Url generateShortUrl(UrlDTO url){

        if(StringUtils.isNotEmpty(url.getUrl())){
            String encodedUrl=encodeUrl(url.getUrl());
            Url urlToPersist=new Url();
            urlToPersist.setOriginalurl(url.getUrl());
            urlToPersist.setShortUrl(encodedUrl);
            urlToPersist.setCreationDate(LocalDateTime.now());
            urlToPersist.setExpirationDate(getExpirationDate(url.getExpirationDate(),urlToPersist.getCreationDate()));
            return  persistShortUrl(urlToPersist);
        }

        return null;

    }

    public Url persistShortUrl(Url urlToPersist) {
        return urlRepository.save(urlToPersist);
    }

    public Url getEncodedUrl(String url){
        return urlRepository.findByShortUrl(url);
    }

    public void deleteShortUrl(Url url){
        urlRepository.delete(url);
    }
    private String encodeUrl(String originalurl) {
        String encodedUrl="";
        LocalDateTime time=LocalDateTime.now();
        encodedUrl= Hashing.murmur3_32_fixed().hashString(originalurl.concat(time.toString()), StandardCharsets.UTF_8).toString();
        return encodedUrl;
    }

    private LocalDateTime getExpirationDate(String expirationDate, LocalDateTime creationDate) {
        if(StringUtils.isEmpty(expirationDate)){
            return creationDate.plusSeconds(60);
        }
        return LocalDateTime.parse(expirationDate);
    }
}
