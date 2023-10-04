package com.hng.stage.HNGx_Stage5_Task.model.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class VideoResponseDTO{

    private String fileName;
    private String fileId;
    private LocalDateTime timeStamp;
    private String fileSize;
    private String fileUrl;
    private String transcription;
}
