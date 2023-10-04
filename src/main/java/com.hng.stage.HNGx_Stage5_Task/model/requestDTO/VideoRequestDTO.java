package com.hng.stage.HNGx_Stage5_Task.model.requestDTO;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoRequestDTO {
    private String fileName;
    private LocalDateTime timeStamp;
    private String fileUrl;
    private Long fileSize;
}
