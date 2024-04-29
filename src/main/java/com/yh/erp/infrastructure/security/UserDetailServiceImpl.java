package com.yh.erp.infrastructure.security;

import com.yh.erp.domain.model.company.Company;
import com.yh.erp.domain.model.company.CompanyRepository;
import com.yh.erp.domain.model.user.User;
import com.yh.erp.domain.model.user.UserRepository;
import com.yh.erp.infrastructure.firebase.FirebaseSelector;
import com.yh.erp.infrastructure.firebase.FirebaseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final FirebaseSelector firebaseSelector;

    private final CompanyRepository companyRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /**
         *
         * 1. 파이어베이스에서 사용자 존재 확인
         * 2. DB에 사용자 존재 확인
         * 3. 존재하지 않으면 DB에 회원 추가
         * 4. 시큐리티 유저정보 생성 후 리턴
         *
         */


        FirebaseUser firebaseUser = getFirebaseUser(username);

        //파이어베이스에 사용자 정보가 없다면 인증실패
        if (firebaseUser == null) {
            return null;
        }

        User originUser = userRepository.getUserByLoginId(username);

        //서버DB에 사용자가 없다면 등록
        if(originUser == null){
            Company company = companyRepository.getYhCompany();

            User user = User.builder()
                    .companyId(company.getId())
                    .loginId(username)
                    .name(firebaseUser.getName())
                    .createAt(firebaseUser.getCreateAt())
                    .build();

            userRepository.save(user);
        }

        return new org.springframework.security.core.userdetails.User(firebaseUser.getUserId(), firebaseUser.getPassword(), new ArrayList());
    }

    private FirebaseUser getFirebaseUser(String username) {
        try {
            Optional<FirebaseUser> optFirebaseUser = firebaseSelector.getUserById(username);

            //파이어베이스에 사용자 등록됐는지 확인
            if(optFirebaseUser.isEmpty()){
                return null;
            }

            return optFirebaseUser.get();

        } catch (Exception e){
            //TODO 예외처리 확인 필요
            throw new RuntimeException();
        }
    }
}
