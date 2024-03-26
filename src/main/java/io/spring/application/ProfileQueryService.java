package io.spring.application;

import com.ibm.icu.impl.Trie2;
import io.spring.application.data.ProfileData;
import io.spring.application.data.UserData;
import io.spring.core.user.User;
import io.spring.infrastructure.mybatis.readservice.UserReadService;
import io.spring.infrastructure.mybatis.readservice.UserRelationshipQueryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProfileQueryService {
  private UserReadService userReadService;
  private UserRelationshipQueryService userRelationshipQueryService;

  public Optional<ProfileData> findByUsername(String username, User currentUser) {
    UserData userData = userReadService.findByUsername(username);
    if (userData == null) {
      return Optional.empty();
    } else {
      ProfileData profileData =
          new ProfileData(
              userData.getId(),
              userData.getUsername(),
              userData.getBio(),
              userData.getImage(),
              currentUser != null
                  && userRelationshipQueryService.isUserFollowing(
                      currentUser.getId(), userData.getId()));
      return Optional.of(profileData);
    }
  }

  public List<ProfileData> findAllProfiles(User currentUser) {
    List<UserData> userDataList = userReadService.findAll();
    List<ProfileData> profileDataList = new ArrayList<>();

    for (UserData userData : userDataList) {
      profileDataList.add(
          new ProfileData(
              userData.getId(),
              userData.getUsername(),
              userData.getBio(),
              userData.getImage(),
              currentUser != null
                  && userRelationshipQueryService.isUserFollowing(
                      currentUser.getId(), userData.getId())));
    }

    return profileDataList;
  }
}
