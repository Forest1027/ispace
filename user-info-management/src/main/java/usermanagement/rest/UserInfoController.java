package usermanagement.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import usermanagement.entity.UserInfo;
import usermanagement.service.UserInfoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userManagement")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @PutMapping("/v1/users/updateEmailVerified")
    public void updateEmailVerified(@RequestBody UserInfo userInfo) {
        if (userInfo.getEmailVerified() == null || userInfo.getId() == null) {
            throw new RuntimeException("emailVerified or id cannot be null");
        }
        userInfoService.updateEmailVerified(userInfo.getId(), userInfo.getEmailVerified());
    }

    @GetMapping("/v1/users/{userId}")
    public Optional<UserInfo> getUserInfoById(@PathVariable int userId) {
        return userInfoService.findUserInfoById(userId);
    }

    @PutMapping("/v1/users/{userId}")
    public UserInfo updateUserInfo(@PathVariable int userId, @RequestBody UserInfo userInfo) {
        userInfo.setId(userId);
        return userInfoService.updateUserInfo(userInfo);
    }

    @DeleteMapping("/v1/users/{userId}")
    public String deleteUserInfoById(@PathVariable int userId) {
        userInfoService.deleteUserInfoById(userId);
        return "Delete successfully";
    }

    @PostMapping("/v1/users/register")
    public UserInfo createUserInfo(@RequestBody UserInfo userInfo) {
        userInfo.setId(null);
        userInfo.setEmailVerified(false);
        return userInfoService.createUserInfo(userInfo);
    }

    @GetMapping("/v1/users")
    public List<UserInfo> getUsers() {
        return userInfoService.getUsers();
    }

}
