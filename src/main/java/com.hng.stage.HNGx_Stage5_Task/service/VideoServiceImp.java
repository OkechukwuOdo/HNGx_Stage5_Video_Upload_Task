package com.hng.stage.HNGx_Stage5_Task.service;

import com.hng.stage.HNGx_Stage5_Task.entity.Video;
import com.hng.stage.HNGx_Stage5_Task.model.responseDTO.ApiResponseDTO;
import com.hng.stage.HNGx_Stage5_Task.model.responseDTO.VideoResponseDTO;
import com.hng.stage.HNGx_Stage5_Task.repository.VideoRepo;
import com.hng.stage.HNGx_Stage5_Task.util.VideoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.hng.stage.HNGx_Stage5_Task.util.VideoUtil.saveVideoToDisk;

@Service
@RequiredArgsConstructor
public class VideoServiceImp implements VideoService{
    private final VideoRepo videoRepository;
    private final VideoUtil videoUtils;

    @Transactional
    public ApiResponseDTO<List<VideoResponseDTO>> uploadVideo(MultipartFile[] UploadedVideos) throws IOException {


        List<VideoResponseDTO> videoResponseDtoList = new ArrayList<>();
        String savedVideoFilePath = null;

        // Save video to disk
        for(MultipartFile multipartFile : UploadedVideos){
            System.out.println("Uploading : "+ multipartFile.getOriginalFilename() + "to the server ");
            try {
                savedVideoFilePath = saveVideoToDisk(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("An Error occurred while writing video file to disk");
            }

            // Generate a unique filename for the video
            assert savedVideoFilePath != null;
            String uploadedFileUrl = StringUtils.cleanPath(savedVideoFilePath);

            // Transcribe the video
            String videoTranscription = VideoUtil.transcribeVideo(savedVideoFilePath);

            // Save the video metadata to the database
            Video videoData = Video.builder()
                    .fileSize(String.valueOf(multipartFile.getSize()))
                    .filename(multipartFile.getOriginalFilename())
                    .timestamp(LocalDateTime.now())
                    .transcriptionText(videoTranscription)
                    .fileUrl(uploadedFileUrl)
                    .build();
            Video videoUploaded = videoRepository.save(videoData);
            VideoResponseDTO videoResponseDto = VideoResponseDTO.builder()
                    .fileUrl(videoUploaded.getFileUrl())
                    .fileId(String.valueOf(videoUploaded.getId()))
                    .timeStamp(videoUploaded.getTimestamp())
                    .fileName(videoUploaded.getFilename())
                    .fileSize(videoUploaded.getFileSize())
                    .transcription(videoTranscription)
                    .build();

            videoResponseDtoList.add(videoResponseDto);
        }


        return new ApiResponseDTO("Upload successfully", 200,videoResponseDtoList);
    }

    @Override
    public ApiResponseDTO<Optional<Video>> findVideoById(String fileId) {

        if(videoRepository.findVideoById(fileId).isPresent())
        {
            return new ApiResponseDTO("Success", 200, videoRepository.findVideoById(fileId));
        }else {
            return new ApiResponseDTO("Video doesn't exist", 200, null);
        }
//        VideoResponseDto videoResponse = VideoResponseDto.builder()
//                .fileSize(response.getFileSize())
//                .downloadUrl(response.getFileUrl())
//                .fileId(response.getId())
//                .fileName(response.getFilename())
//                .timeStamp(response.getTimestamp())
//                .build();
    }

    @Override
    public ApiResponseDTO<List<VideoResponseDTO>> getAllVideos() {
        List<VideoResponseDTO> response = videoRepository.findAll()
                .stream()
//                .map(video -> videoUtils.mapVideoToDto(video)).toList();
                .map(videoUtils::mapVideoToDto).toList();
        return new ApiResponseDTO("All video successfully fetched", 200, response);
    }

    @Override
    public ApiResponseDTO<List<VideoResponseDTO>> findByFileName(String fileName) {
        List<VideoResponseDTO> response = videoRepository.findByFilename(fileName)
                .stream()
//                .map(video -> videoUtils.mapVideoToDto(video)).toList();
                .map(videoUtils::mapVideoToDto).toList();
        return new ApiResponseDTO("Fetch successfully", 200, response);
    }

    @Override
    public ApiResponseDTO<String> deleteFileById(String fileId) {
        ;
        if(videoRepository.findVideoById(fileId).isPresent())
        {
            videoRepository.deleteById(fileId);
            return new ApiResponseDTO("Deleted successfully", 200, null);
        }else {
            return new ApiResponseDTO("Fails doesn't exist", 200, null);
        }
    }
}
