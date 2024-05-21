package com.example.shoppingmall.user;

import com.example.shoppingmall.utils.ApiUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.example.shoppingmall.utils.ApiUtils.error;
import static com.example.shoppingmall.utils.ApiUtils.success;

@Slf4j
@AllArgsConstructor
@RestController
public class UserController {

    UserService userService;

    private boolean isDuplicateId(UserDTO userDTO) {
        return userService.checkDuplicateId(userDTO.getUserId());
    }

    //유효성 검사하다가 에러가 터지면 호출되는 예외 처리 메소드
//    @ExceptionHandler // (MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ApiUtils.ApiResult<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException errors){
//        Map<String, String> errorMessages = new HashMap<>();
//
//        for (FieldError error : errors.getFieldErrors()) {
//            String errorField = error.getField();
//            String errorMessage = error.getDefaultMessage();
//            errorMessages.put(errorField, errorMessage);
//        }
//
//        return error(errorMessages, HttpStatus.BAD_REQUEST);
//
//    }

//    @GetMapping("/datasource")
//    public void makeConnection() {
//        userService.makeConnection();
//    }

    @PostMapping("/join")
    public ApiUtils.ApiResult join(@Valid @RequestBody UserDTO userDTO) {

        if (isDuplicateId(userDTO))
            return error("아이디 중복", HttpStatus.CONFLICT);

        User requestUser = userDTO.convertToEntity();
        String userId = userService.join(requestUser);
        return success(userId);
    }

    @PostMapping("/login")
    public ApiUtils.ApiResult login(@Valid @RequestBody UserDTO userDTO) {

        User requestUser = userDTO.convertToEntity();
        int userId = userService.login(requestUser);
        return success(userId);
    }
//    @PostMapping("/join")
//    public ResponseEntity<String> join(@RequestBody User user) {
//        log.info(user.toString());
//        String userId = userService.join(user);
//
//        if (userService.checkDuplicateId(userId))
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        return new ResponseEntity<>(userId, HttpStatus.OK);
//    }

//    @PostMapping("/join")
//    public ApiUtils.ApiResult joinByApiResult(
//            @Valid @RequestBody UserDTO userDTO, Errors errors){
//
//        if (errors.hasErrors()) {
//                Map<String, String> errorMessages = new HashMap<>();
//
//                for (FieldError error : errors.getFieldErrors()) {
//                    // 예외 field명
//                    String errorField = error.getField();
//                    // 예외 message
//                    String errorMessage = error.getDefaultMessage();
//                    errorMessages.put(errorField, errorMessage);
//                }
//
//                return error(errorMessages, HttpStatus.BAD_REQUEST);
//        }
//
//        if (isDuplicateId(userDTO))
//            return error("아이디 중복", HttpStatus.CONFLICT);
//
//        User requestUser = userDTO.convertToEntity();
//        String userId = userService.join(requestUser);
//        return success(userId);
//    }



//    private ApiResult ApiResultForConflict() {
//        return new ApiResult<>(false, null,
//                new ApiResult.ApiError("아이디 중복", HttpStatus.CONFLICT));
//    }
//
//    private ApiResult ApiResultForSuccess(String userId) {
//        return new ApiResult<>(true, userId, null);
//    }
}
