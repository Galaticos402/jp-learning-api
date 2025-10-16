package com.self.jplearning.repository;

import com.self.jplearning.entity.RepetitionTrack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IRepetitionTrackRepository extends JpaRepository<RepetitionTrack, UUID> {
}
