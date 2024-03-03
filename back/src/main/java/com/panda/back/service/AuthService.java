package com.panda.back.service;

import org.springframework.http.ResponseEntity;

import com.panda.back.dto.request.auth.CheckCertificationRequestDto;
import com.panda.back.dto.request.auth.EmailCertificationRequestDto;
import com.panda.back.dto.request.auth.IdCheckRequestDto;
import com.panda.back.dto.request.auth.SignInRequestDto;
import com.panda.back.dto.request.auth.SignUpRequestDto;
import com.panda.back.dto.response.auth.CheckCertificationResponseDto;
import com.panda.back.dto.response.auth.EmailCertificationResponseDto;
import com.panda.back.dto.response.auth.IdCheckResponseDto;
import com.panda.back.dto.response.auth.SignInResponseDto;
import com.panda.back.dto.response.auth.SignUpResponseDto;

public interface AuthService {
  ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);

  ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);

  ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);

  ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);

  ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);

}
