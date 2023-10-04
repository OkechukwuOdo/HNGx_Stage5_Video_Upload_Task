package com.hng.stage.HNGx_Stage5_Task.model.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ApiResponseDTO<T> {
    private String message;
    private Integer statusCode;
    private T data;
}
