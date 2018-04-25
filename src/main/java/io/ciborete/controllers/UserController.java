package io.ciborete.controllers;
import io.ciborete.dto.Request;
import io.ciborete.model.User;
import io.ciborete.model.UserSettings;
import io.ciborete.service.UserService;
import io.ciborete.service.UserSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserSettingsService userSettingsService;

    @RequestMapping(path="/add",method = {RequestMethod.POST})
    public ResponseEntity<?> addUser(@RequestBody User user) {
        User createdUser = userService.addUser(user);
        return (createdUser != null) ?
                (new ResponseEntity<User>(createdUser, HttpStatus.CREATED)) :
                (new ResponseEntity<User>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<User> findUsers() {
        return userService.findUsers();
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<User> findUser(@PathVariable String userId) {
        User user = userService.findUser(userId);
        return (user != null) ?
                new ResponseEntity<User>(user, HttpStatus.OK) :
                new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path ="/ids",method = RequestMethod.POST)
    public List<User> findUserByIds(@RequestBody List<String> userIds) {
        return userService.findUsersByIds(userIds);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<User>> findUsers(@RequestBody Request request) {
        System.out.println(request.toString());
        if(request==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userService.findUsers(request),HttpStatus.OK);
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable String userId, @RequestBody User user) {
        return userService.updateUser(userId, user);
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }

    @RequestMapping(path = "/settings", method = RequestMethod.POST)
    public UserSettings addUserSettings(@RequestBody UserSettings userSettings) {
        return userSettingsService.addUserSettings(userSettings);
    }

    @RequestMapping(path = "/settings/{userId}", method = RequestMethod.PUT)
    public UserSettings updateUserSettings(@PathVariable String userId, @RequestBody UserSettings userSettings) {
        return userSettingsService.updateUserSettings(userId, userSettings);
    }

    @RequestMapping(path = "/settings/loggedInUser", method = RequestMethod.GET)
    public ResponseEntity<UserSettings> getUserSettings() {
        UserSettings userSettings = userSettingsService.findCurrentUserSettings();
        return (userSettings!=null) ?
                new ResponseEntity<>(userSettings,HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
