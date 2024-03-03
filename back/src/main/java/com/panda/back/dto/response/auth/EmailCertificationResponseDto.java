package com.panda.back.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.panda.back.common.ResponseCode;
import com.panda.back.common.ResponseMessage;
import com.panda.back.dto.response.ResponseDTO;

import lombok.Getter;

@Getter
public class EmailCertificationResponseDto extends ResponseDTO {
  private EmailCertificationResponseDto() {
    super();
  }

  public static ResponseEntity<EmailCertificationResponseDto> success() {
    EmailCertificationResponseDto responseBody = new EmailCertificationResponseDto();
    return ResponseEntity.status(HttpStatus.OK).body(responseBody);
  }

  public static ResponseEntity<ResponseDTO> duplicateId() {
    ResponseDTO responseBody = new ResponseDTO(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
  }

  public static ResponseEntity<ResponseDTO> mailSendFail() {
    ResponseDTO responseBody = new ResponseDTO(ResponseCode.MAIL_FAIL, ResponseMessage.MAIL_FAIL);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
  }

}
