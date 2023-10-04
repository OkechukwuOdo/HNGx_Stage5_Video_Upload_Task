package com.hng.stage.HNGx_Stage5_Task.repository;

import com.hng.stage.HNGx_Stage5_Task.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideoRepo extends JpaRepository<Video,String> {
    // Custom query to find videos by filename
    List<Video> findByFilename(String fileName);
    // Custom query to find videos uploaded after a certain timestamp
    Optional<Video> findVideoById(String id);
}
