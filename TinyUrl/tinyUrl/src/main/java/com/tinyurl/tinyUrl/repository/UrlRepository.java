package com.tinyurl.tinyUrl.repository;

import com.tinyurl.tinyUrl.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url,Long> {
    public Url findByShortUrl(String shortUrl);
}
