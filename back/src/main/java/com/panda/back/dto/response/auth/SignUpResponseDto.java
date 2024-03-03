package com.panda.back.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.panda.back.common.ResponseCode;
import com.panda.back.common.ResponseMessage;
import com.panda.back.dto.response.ResponseDTO;

import lombok.Getter;

@Getter
public class SignUpResponseDto extends ResponseDTO {
  private SignUpResponseDto() {
    super();
  }

  public static ResponseEntity<SignUpResponseDto> success() {
    SignUpResponseDto responseBody = new SignUpResponseDto();
    return ResponseEntity.status(HttpStatus.OK).body(responseBody);
  }

  public static ResponseEntity<ResponseDTO> duplicated() {
    ResponseDTO responseBody = new ResponseDTO(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
  }

  public static ResponseEntity<ResponseDTO> certificationFail() {
    ResponseDTO responseBody = new ResponseDTO(ResponseCode.CERTIFICATION_FAIL, ResponseMessage.CERTIFICATION_FAIL);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
  }

}
