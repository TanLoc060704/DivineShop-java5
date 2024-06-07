package poly.java5divineshop.Divineshop.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poly.java5divineshop.Divineshop.Data.Model.UserM;
import poly.java5divineshop.Divineshop.Service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api-User")
@Slf4j
public class UserAPI {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        Map<String, Object> result = new HashMap<>();
        try{
            List<UserM> lisUserM = userService.findAll();
            if (lisUserM == null){
                result.put("success", false);
                result.put("message", "Call API thành công");
                result.put("data", null);
            }
            result.put("success", true);
            result.put("message", "Call API thành công");
            result.put("data", lisUserM);
            return ResponseEntity.ok(result);
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Call API thất bại");
            result.put("data", "error");
            e.printStackTrace();
            return ResponseEntity.ok(result);
        }
    }
}
