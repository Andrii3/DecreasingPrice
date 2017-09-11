package com.decreasingPrice.service;

import java.util.List;

import com.decreasingPrice.model.UserProfile;

public interface UserProfileService {

    UserProfile findById(int id);

    UserProfile findByType(String type);

    List<UserProfile> findAll();

}
