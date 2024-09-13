package chunggyeong.bibliophile.domain.interest.service;

import chunggyeong.bibliophile.domain.interest.domain.Classification;
import chunggyeong.bibliophile.domain.interest.domain.Interest;
import chunggyeong.bibliophile.domain.interest.domain.repository.InterestRepository;
import chunggyeong.bibliophile.domain.interest.exception.DuplicateClassificationException;
import chunggyeong.bibliophile.domain.interest.exception.InterestLimitExceededException;
import chunggyeong.bibliophile.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterestService implements InterestServiceUtils {

    private final InterestRepository interestRepository;

    // 관심사 등록
    @Override
    @Transactional
    public void addInterest(User user, Classification classification) {
        if (4 <= interestRepository.countByUser(user)) {
            throw InterestLimitExceededException.EXCEPTION;
        }

        if (interestRepository.existsByUserAndClassification(user, classification)) {
            throw DuplicateClassificationException.EXCEPTION;
        }

        Interest interest = Interest.createInterest(user, classification);

        interestRepository.save(interest);
    }

    // 유저의 관심사 조회
    @Override
    public List<Classification> findInterestsByUser(User user) {
        return interestRepository.findAllByUser(user).stream()
                .map(Interest::getClassification)
                .collect(Collectors.toList());
    }

    // 유저의 관심사 모두 삭제
    @Override
    public void deleteAllByUser(User user) {
        interestRepository.deleteAllByUser(user);
    }
}
