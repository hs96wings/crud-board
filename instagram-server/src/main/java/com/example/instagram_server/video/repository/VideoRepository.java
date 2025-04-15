package com.example.instagram_server.video.repository;

import com.example.instagram_server.video.domain.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findByVideoId(String videoId);
    Page<Video> findByTitleContaining(String keyword, Pageable pageable);
}
