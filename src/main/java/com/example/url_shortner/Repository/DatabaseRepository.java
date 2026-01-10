package com.example.url_shortner.Repository;

import com.example.url_shortner.Entity.UrlData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DatabaseRepository extends JpaRepository<UrlData,Long> {
}
