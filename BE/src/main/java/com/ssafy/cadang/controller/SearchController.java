package com.ssafy.cadang.controller;

import com.ssafy.cadang.domain.Cafes;
import com.ssafy.cadang.domain.Drinks;
import com.ssafy.cadang.domain.User;
import com.ssafy.cadang.dto.UserAmount;
import com.ssafy.cadang.response.DrinkResponseDTO;
import com.ssafy.cadang.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SearchController {

    private final KakaoService kakaoService;
    private final SearchService searchService;
    private final SearchLogService searchLogService;

    // 검색량 높은 키워드 순위
    // 기록량 높은 음료 순위
    // 음료 전체보기
    @GetMapping("/drinks")
    public ResponseEntity<?> allDrink(@RequestHeader("Authorization") String token) {

        System.out.println("음료전체보기" + token);

        // 토큰 유효성 검사
        String passAccess = kakaoService.checkToken(token); // 통과한 access token
        System.out.println(" 유효성검사 시작 "+passAccess);
        if (passAccess == null) {
            System.out.println("유효성검사 실패");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<DrinkResponseDTO> list = searchService.drinkList();

        return new ResponseEntity<List<DrinkResponseDTO>>(list, HttpStatus.OK);
    }

    // 음료 검색하기
    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> searchList(@RequestHeader("Authorization") String token,@PathVariable String keyword ) {
        // 토큰 유효성 검사
        String passAccess = kakaoService.checkToken(token); // 통과한 access token
        System.out.println(" 유효성검사 시작 "+passAccess);
        if (passAccess == null) {
            System.out.println("유효성검사 실패");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // jwt를 이용해 user 가져오기
        User user = kakaoService.getUser(passAccess);

        searchLogService.log(user.getUserId(),keyword);
        List<DrinkResponseDTO> list = searchService.keywordList(keyword);
        return new ResponseEntity<List<DrinkResponseDTO>>(list, HttpStatus.OK);
    }
}
