package com.panda.back.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.panda.back.common.ResponseCode;
import com.panda.back.common.ResponseMessage;
import com.panda.back.dto.response.ResponseDTO;

import lombok.Getter;

@Getter
public class CheckCertificationResponseDto extends ResponseDTO {
  public CheckCertificationResponseDto() {
    super();
  }

  public static ResponseEntity<CheckCertificationResponseDto> success() {
    CheckCertificationResponseDto responseBody = new CheckCertificationResponseDto();
    return ResponseEntity.status(HttpStatus.OK).body(responseBody);
  }

  public static ResponseEntity<ResponseDTO> certificationFail() {
    ResponseDTO responseBody = new ResponseDTO(ResponseCode.CERTIFICATION_FAIL, ResponseMessage.CERTIFICATION_FAIL);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
  }
}
