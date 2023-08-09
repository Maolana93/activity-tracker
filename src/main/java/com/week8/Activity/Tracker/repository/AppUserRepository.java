package com.week8.Activity.Tracker.repository;

import com.week8.Activity.Tracker.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository  extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByEmail(String email);
}

