package com.panda.back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.panda.back.common.CertificationNumber;
import com.panda.back.dto.request.auth.CheckCertificationRequestDto;
import com.panda.back.dto.request.auth.EmailCertificationRequestDto;
import com.panda.back.dto.request.auth.IdCheckRequestDto;
import com.panda.back.dto.request.auth.SignInRequestDto;
import com.panda.back.dto.request.auth.SignUpRequestDto;
import com.panda.back.dto.response.ResponseDTO;
import com.panda.back.dto.response.auth.CheckCertificationResponseDto;
import com.panda.back.dto.response.auth.EmailCertificationResponseDto;
import com.panda.back.dto.response.auth.IdCheckResponseDto;
import com.panda.back.dto.response.auth.SignInResponseDto;
import com.panda.back.dto.response.auth.SignUpResponseDto;
import com.panda.back.entity.CertificationEntity;
import com.panda.back.entity.UserEntity;
import com.panda.back.provider.EmailProvider;
import com.panda.back.provider.JwtProvider;
import com.panda.back.repository.CertificationRepositoty;
import com.panda.back.repository.UserRepositoty;
import com.panda.back.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

  private final UserRepositoty userRepositoty;
  private final CertificationRepositoty certificationRepositoty;
  private final EmailProvider emailProvider;
  private final JwtProvider jwtProvider;
  private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Override
  public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {
    try {
      String userId = dto.getId();
      boolean isExistId = userRepositoty.existsByUserId(userId);
      if (isExistId)
        return IdCheckResponseDto.duplicateId();
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDTO.databaseError();
    }
    return IdCheckResponseDto.success();
  }

  @Override
  public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {

    try {
      String userId = dto.getId();
      String email = dto.getEmail();
      boolean isExistId = userRepositoty.existsByUserId(userId);
      if (isExistId)
        return EmailCertificationResponseDto.duplicateId();
      // 인증 코드 만들기
      String certificationNumber = CertificationNumber.getCertificationNumber();
      boolean isSuccessed = emailProvider.sendCertifiacationMail(email, certificationNumber);
      if (!isSuccessed)
        return EmailCertificationResponseDto.mailSendFail();
      CertificationEntity certificationEntity = new CertificationEntity(userId, email, certificationNumber);
      certificationRepositoty.save(certificationEntity);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDTO.databaseError();
    }
    return EmailCertificationResponseDto.success();
  }

  @Override
  public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {
    try {
      String userId = dto.getId();
      String email = dto.getEmail();
      String certificationNumber = dto.getCertificationNumber();

      CertificationEntity certificationEntity = certificationRepositoty.findByUserId(userId);
      if (certificationEntity == null)
        return CheckCertificationResponseDto.certificationFail();
      boolean isMatched = certificationEntity.getEmail().equals(email)
          && certificationEntity.getCertificationNumber().equals(certificationNumber);

      if (!isMatched)
        return CheckCertificationResponseDto.certificationFail();
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDTO.databaseError();
    }
    return CheckCertificationResponseDto.success();
  }

  @Override
  public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
    try {
      String userId = dto.getId();
      boolean isExistId = userRepositoty.existsByUserId(userId);
      if (isExistId)
        return SignUpResponseDto.duplicated();

      String email = dto.getEmail();
      String certificationNumber = dto.getCertificationNumber();
      // table certification_number
      CertificationEntity certificationEntity = certificationRepositoty.findByUserId(userId);
      boolean isMatched = certificationEntity.getEmail().equals(email)
          && certificationEntity.getCertificationNumber().equals(certificationNumber);
      if (!isMatched)
        return SignUpResponseDto.certificationFail();
      // password 암호화
      String password = dto.getPassword();
      String encodePassword = passwordEncoder.encode(password);
      dto.setPassword(encodePassword);
      UserEntity userEntity = new UserEntity(dto);
      userRepositoty.save(userEntity);
      // 가입이 완료되면 certification 테이블 내의 유저는 필요없다.
      certificationRepositoty.deleteByUserId(userId);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDTO.databaseError();
    }
    return SignUpResponseDto.success();
  }

  @Override
  public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
    String token = null;
    try {
      String userId = dto.getId();
      UserEntity userEntity = userRepositoty.findByUserId(userId);
      if (userEntity == null) {
        return SignInResponseDto.signInFail();
      }
      String password = dto.getPassword();
      String encodePassword = userEntity.getPassword();
      boolean isMatched = passwordEncoder.matches(password, encodePassword);
      if (!isMatched) {
        return SignInResponseDto.signInFail();
      }
      // token 생성
      token = jwtProvider.create(userId);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDTO.databaseError();
    }

    return SignInResponseDto.success(token);
  }
}
