package com.hng.stage.HNGx_Stage5_Task.service;

import com.hng.stage.HNGx_Stage5_Task.entity.Video;
import com.hng.stage.HNGx_Stage5_Task.model.responseDTO.ApiResponseDTO;
import com.hng.stage.HNGx_Stage5_Task.model.responseDTO.VideoResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface VideoService {
    ApiResponseDTO<List<VideoResponseDTO>> uploadVideo(MultipartFile[] file) throws IOException;

    ApiResponseDTO<Optional<Video>> findVideoById(String id);

    ApiResponseDTO<List<VideoResponseDTO>> getAllVideos();

    ApiResponseDTO<List<VideoResponseDTO>> findByFileName(String fileName);

    ApiResponseDTO<String> deleteFileById(String fileId);
}
