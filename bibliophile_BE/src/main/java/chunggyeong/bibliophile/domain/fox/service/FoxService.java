package chunggyeong.bibliophile.domain.fox.service;

import chunggyeong.bibliophile.domain.fox.domain.Fox;
import chunggyeong.bibliophile.domain.fox.domain.FoxStatus;
import chunggyeong.bibliophile.domain.fox.domain.repository.FoxRepository;
import chunggyeong.bibliophile.domain.fox.exception.AlreadyHasFoxException;
import chunggyeong.bibliophile.domain.fox.exception.FoxNotFoundException;
import chunggyeong.bibliophile.domain.fox.presentation.dto.response.FoxResponse;
import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.global.utils.user.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoxService implements FoxServiceUtils{

    private final FoxRepository foxRepository;
    private final UserUtils userUtils;

    // 내 여우 생성
    @Transactional
    public FoxResponse addMyFox(){
        User user = userUtils.getUserFromSecurityContext();
        Fox fox = Fox.createFox(user);

        if(foxRepository.existsByUser(user)){
            throw AlreadyHasFoxException.EXCEPTION;
        }

        log.info("User {} create a fox", user);
        foxRepository.save(fox);
        return new FoxResponse(fox);
    }

    // 내 여우 조회
    public FoxResponse findMyFoxByUser(){
        User user = userUtils.getUserFromSecurityContext();
        log.info("User {} find a fox", user);
        Fox fox = queryFoxByUser(user);
        if(fox.getLastModifyDate().plusHours(24).isBefore(LocalDateTime.now())){
            fox.updateFoxStatus(FoxStatus.BAD);
        }
        return new FoxResponse(fox);
    }

    // 내 여우 먹이 주기를 통한 FeedCount 및 level 증가
    @Transactional
    public FoxResponse updateMyFoxFeedCount(){
        User user = userUtils.getUserFromSecurityContext();
        log.info("User {} update a fox", user);
        Fox fox = queryFoxByUser(user);
        fox.updateExpCount();
        return new FoxResponse(fox);
    }

    @Override
    public Fox queryFoxByUser(User user){
        return foxRepository.findByUser(user).orElseThrow(()->FoxNotFoundException.EXCEPTION);
    }
}
