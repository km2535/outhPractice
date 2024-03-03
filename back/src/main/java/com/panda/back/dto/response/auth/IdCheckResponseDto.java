package com.panda.back.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.panda.back.common.ResponseCode;
import com.panda.back.common.ResponseMessage;
import com.panda.back.dto.response.ResponseDTO;

import lombok.Getter;

@Getter
public class IdCheckResponseDto extends ResponseDTO {
  private IdCheckResponseDto() {
    super();
  }

  public static ResponseEntity<IdCheckResponseDto> success() {
    IdCheckResponseDto responseBody = new IdCheckResponseDto();
    return ResponseEntity.status(HttpStatus.OK).body(responseBody);
  }

  public static ResponseEntity<ResponseDTO> duplicateId() {
    ResponseDTO responseBody = new ResponseDTO(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
  }

}
