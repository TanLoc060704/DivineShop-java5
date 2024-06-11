package poly.java5divineshop.Divineshop.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.java5divineshop.Divineshop.Data.Dto.OderDTO;
import poly.java5divineshop.Divineshop.Data.Dto.ProductDTO;
import poly.java5divineshop.Divineshop.Data.Dto.UserDto;
import poly.java5divineshop.Divineshop.Data.Entity.OderE;
import poly.java5divineshop.Divineshop.Data.Entity.ProductE;
import poly.java5divineshop.Divineshop.Data.Entity.UserE;
import poly.java5divineshop.Divineshop.Data.Model.CategoryM;
import poly.java5divineshop.Divineshop.Data.Model.OderM;
import poly.java5divineshop.Divineshop.Data.Model.ProductM;
import poly.java5divineshop.Divineshop.Data.Model.UserM;
import poly.java5divineshop.Divineshop.Service.OderService;
import poly.java5divineshop.Divineshop.Service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/oder")
@Slf4j
public class OderAPI {

    @Autowired
    OderService oderService;

    @Autowired
    UserService userService;

    //lấy full thông tin ở bảng order
    @GetMapping
    public ResponseEntity<?> findAllOder(){
        Map<String, Object> result = new HashMap<>();
        try {
            List<OderM> list = oderService.findAll();
            result.put("success", true);
            result.put("message", "Lấy thông tin danh mục thành công");
            result.put("data", list);
        } catch (Exception e) {
            log.error("Lấy thông tin danh mục thất bại!!!", e);
            result.put("success", false);
            result.put("message", "Lấy thông tin danh mục thất bại");
            result.put("data", null);
            return ResponseEntity.internalServerError().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> saveOder(@RequestBody OderDTO oderDTO){
        Map<String, Object> result = new HashMap<>();
        try{
            result.put("success", true);
            result.put("message", "Lưu order thành công");
            result.put("data", oderDTO);
            for(int i  = 0; i < oderDTO.getListProduct().size() ; i++){
//                System.out.println("id-product "+ oderDTO.getListProduct().get(i).getId());
                oderDTO.setProductE(oderDTO.getListProduct().get(i));//set từng idproduct vào order
                oderDTO.setUserE(userService.findBytenDangNhap(oderDTO.getUserE().getTenDangNhap())); //lấy tên user để tìm id user đó
                OderE oderE = OderDTO.convertOderDtoToOderE(oderDTO);// chuyển đổi thành e để lưu vào database
//                System.out.println("oderE đã set idproduct  "+oderE.getProductE().getId());
//                System.out.println("user id = " +oderE.getUserE().getTenDangNhap());
                //lưu vào database
                oderService.save(oderE);
            }
        }catch (Exception e){
            log.error("Lỗi khi save order", e);
            result.put("success", false);
            result.put("message", "lưu order thất bại");
            result.put("data", null);
            return ResponseEntity.internalServerError().body(result);
        }
        return ResponseEntity.ok(result);
    }
}
