package com.tinyurl.tinyUrl.service;

import com.tinyurl.tinyUrl.model.Url;
import com.tinyurl.tinyUrl.repository.UrlRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UrlService {
    UrlRepository urlRepository;
    UrlService(UrlRepository urlRepository){
        this.urlRepository=urlRepository;
    }
    public Url generateShortUrl(Url url){

        if(StringUtils.isEmpty(url.getOriginalurl())){
            String encodedUrl=encodeUrl(url.getOriginalurl());
            Url urlToPersist=new Url();
            urlToPersist.setOriginalurl(url.getOriginalurl());
            urlToPersist.setShortUrl(encodedUrl);
            urlToPersist.setCreationDate(LocalDateTime.now());
            urlToPersist.setExpirationDate(getExpirationDate(url.getExpirationDate(),url.getCreationDate()));
            Url responseUrl=persistShortUrl(urlToPersist);
        }
    }

    private Url persistShortUrl(Url urlToPersist) {
        return urlRepository.save(urlToPersist);
    }

    private String encodeUrl(String originalurl) {

    }

    private LocalDateTime getExpirationDate(LocalDateTime expirationDate, LocalDateTime creationDate) {
        if(StringUtils.isEmpty(String.valueOf(expirationDate))){
            return creationDate.plusHours(24);
        }

        return expirationDate;
        
    }
}
