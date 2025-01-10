package com.example.demo.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PharmacyController {
	String KAKAO_API_URL = "https://dapi.kakao.com/v2/local/search/keyword.json";
    private static final String KAKAO_API_KEY = "21f97888347ec234c361174396612f61";

    @GetMapping("/pharmacies")
    public String getPharmacies(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        
        String encodedQuery = URLEncoder.encode("약국", StandardCharsets.UTF_8);
        log.info("Encoded Query: {}", encodedQuery);
                        
        // 요청 URL 구성
        String requestUrl = KAKAO_API_URL +
//        		"?category_group_code=AT4" +
        		"?query=" + encodedQuery +
                "&y=37.517735386458845" +   // 중심 좌표 위도
                "&x=126.90024267705954" +   // 중심 좌표 경도
                "&radius=20000"          // 반경 거리
                ;
        
        log.info("Request URL: {}", requestUrl);
        
        // HTTP 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + KAKAO_API_KEY);
        log.info("Authorization Header: {}", headers.get("Authorization"));
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        // API 호출
        ResponseEntity<Map> response = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, Map.class);

        // 응답 데이터에서 place_url 추출
        List<String> placeUrls = ((List<Map<String, Object>>) response.getBody().get("documents"))
                .stream()
                .map(doc -> (String) doc.get("place_name"))
                .collect(Collectors.toList());

        // 모델에 추가하여 타임리프로 전달
        model.addAttribute("placeUrls", placeUrls);
        if(!placeUrls.isEmpty()) {placeUrls.forEach(url -> log.info(url));}
        else {log.info("결과없음");}
    
        return "pharmacies"; // pharmacies.html로 데이터 전달
    }
}