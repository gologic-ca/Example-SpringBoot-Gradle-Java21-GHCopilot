package io.spring.api;

import io.spring.api.exception.ResourceNotFoundException;
import io.spring.application.ProfileQueryService;
import io.spring.application.data.ProfileData;
import io.spring.core.user.FollowRelation;
import io.spring.core.user.User;
import io.spring.core.user.UserRepository;
import java.util.HashMap;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "profiles/{username}")
@AllArgsConstructor
public class ProfileApi {
  private ProfileQueryService profileQueryService;
  private UserRepository userRepository;

  /**
   * Retrieves the profile information for a given username.
   *
   * @param username the username of the profile to retrieve
   * @param user the authenticated user making the request
   * @return the ResponseEntity containing the profile information
   * @throws ResourceNotFoundException if the profile is not found
   */
  @GetMapping
  public ResponseEntity getProfile(
      @PathVariable String username, @AuthenticationPrincipal User user) {
    return profileQueryService
        .findByUsername(username, user)
        .map(this::profileResponse)
        .orElseThrow(ResourceNotFoundException::new);
  }

  // // add new endpoints who retrieve all profiles and return a ResponseEntity
  // @GetMapping("/all")
  // public ResponseEntity getAllProfiles(@PathVariable("username") String username,
  // @AuthenticationPrincipal User user) {
  //   Map<String, Object> response = new HashMap<>();
  //   response.put("profiles", profileQueryService.findAllProfiles(user));

  //   return ResponseEntity.ok(response);
  // }

  /**
   * Follows a user with the given username.
   *
   * @param username The username of the user to follow.
   * @param user The authenticated user.
   * @return The updated profile information as a ResponseEntity.
   * @throws ResourceNotFoundException If the user to follow is not found.
   */
  @PostMapping(path = "follow")
  public ResponseEntity follow(@PathVariable String username, @AuthenticationPrincipal User user) {
    return userRepository
        .findByUsername(username)
        .map(
            target -> {
              FollowRelation followRelation = new FollowRelation(user.getId(), target.getId());
              userRepository.saveRelation(followRelation);
              return profileResponse(profileQueryService.findByUsername(username, user).get());
            })
        .orElseThrow(ResourceNotFoundException::new);
  }

  /**
   * Unfollows a user with the given username.
   *
   * @param username The username of the user to unfollow.
   * @param user The authenticated user.
   * @return The updated profile information as a ResponseEntity.
   * @throws ResourceNotFoundException If the user to unfollow is not found.
   */
  @DeleteMapping(path = "follow")
  public ResponseEntity unfollow(
      @PathVariable String username, @AuthenticationPrincipal User user) {
    Optional<User> userOptional = userRepository.findByUsername(username);
    if (userOptional.isPresent()) {
      User target = userOptional.get();
      return userRepository
          .findRelation(user.getId(), target.getId())
          .map(
              relation -> {
                userRepository.removeRelation(relation);
                return profileResponse(profileQueryService.findByUsername(username, user).get());
              })
          .orElseThrow(ResourceNotFoundException::new);
    } else {
      throw new ResourceNotFoundException();
    }
  }

  /**
   * Creates a ResponseEntity with the profile information.
   *
   * @param profile The profile data.
   * @return The ResponseEntity containing the profile information.
   */
  private ResponseEntity profileResponse(ProfileData profile) {
    return ResponseEntity.ok(
        new HashMap<String, Object>() {
          {
            put("profile", profile);
          }
        });
  }
}
