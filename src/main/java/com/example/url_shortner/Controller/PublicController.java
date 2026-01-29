package com.example.url_shortner.Controller;

import com.example.url_shortner.Dto.LongUrlDto;
import com.example.url_shortner.Dto.ShortUrlDto;
import com.example.url_shortner.Services.PublicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RequestMapping("/public")
public class PublicController {

    private PublicService publicService;

   public PublicController(PublicService publicService) {
       this.publicService = publicService;
   }

   @PostMapping
   public ResponseEntity<?> saveData(@RequestBody LongUrlDto longUrlDto) {
       try{
           if(longUrlDto.getLongUrl()==null || longUrlDto.getLongUrl().isEmpty()){
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LongUrl is empty");
           }
           ShortUrlDto shortUrlDto = new ShortUrlDto();
           shortUrlDto.setShortUrl("http://localhost:8080/"+publicService.save(longUrlDto));
           return ResponseEntity.status(HttpStatus.CREATED).body(shortUrlDto);
       }
       catch(Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please Try again");
       }
   }
}
