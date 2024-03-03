package com.panda.back.controller;

import org.springframework.web.bind.annotation.RestController;

import com.panda.back.dto.request.auth.CheckCertificationRequestDto;
import com.panda.back.dto.request.auth.EmailCertificationRequestDto;
import com.panda.back.dto.request.auth.IdCheckRequestDto;
import com.panda.back.dto.request.auth.SignInRequestDto;
import com.panda.back.dto.request.auth.SignUpRequestDto;
import com.panda.back.dto.response.auth.EmailCertificationResponseDto;
import com.panda.back.dto.response.auth.CheckCertificationResponseDto;
import com.panda.back.dto.response.auth.IdCheckResponseDto;
import com.panda.back.dto.response.auth.SignInResponseDto;
import com.panda.back.dto.response.auth.SignUpResponseDto;
import com.panda.back.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/id-check")
  public ResponseEntity<? super IdCheckResponseDto> idCheck(@RequestBody @Valid IdCheckRequestDto requestBody) {
    ResponseEntity<? super IdCheckResponseDto> response = authService.idCheck(requestBody);
    return response;
  }

  @PostMapping("/email-certification")
  public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(
      @RequestBody @Valid EmailCertificationRequestDto requestBody) {
    ResponseEntity<? super EmailCertificationResponseDto> response = authService.emailCertification(requestBody);
    return response;
  }

  @PostMapping("/check-certification")
  public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(
      @RequestBody @Valid CheckCertificationRequestDto requestBody) {
    ResponseEntity<? super CheckCertificationResponseDto> response = authService.checkCertification(requestBody);
    return response;
  }

  @PostMapping("/sign-up")
  public ResponseEntity<? super SignUpResponseDto> signUp(
      @RequestBody @Valid SignUpRequestDto requestBody) {
    ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
    return response;
  }

  @PostMapping("/sign-in")
  public ResponseEntity<? super SignInResponseDto> signIn(
      @RequestBody @Valid SignInRequestDto requestBody) {
    ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
    return response;
  }

}
