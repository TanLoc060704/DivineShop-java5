package poly.java5divineshop.Divineshop.Api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.java5divineshop.Divineshop.Data.Dto.CategoryDTO;
import poly.java5divineshop.Divineshop.Data.Dto.CommentDTO;
import poly.java5divineshop.Divineshop.Data.Dto.ProductDTO;
import poly.java5divineshop.Divineshop.Data.Entity.CommentE;
import poly.java5divineshop.Divineshop.Data.Model.CategoryM;
import poly.java5divineshop.Divineshop.Data.Model.ProductM;
import poly.java5divineshop.Divineshop.Repo.CommentRepo;
import poly.java5divineshop.Divineshop.Service.CommentService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
@Slf4j
public class CommentAPI {
    @Autowired
    CommentService commentService;

    @GetMapping
    public ResponseEntity<?> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getComment(@PathVariable int id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentDTO commentDTO) {
        Map<String, Object> result = new HashMap<>();
//        System.out.println(file.getName());
        try {
            CommentDTO comment = commentService.createComment(commentDTO);
            result.put("success", true);
            result.put("message", "Thêm sản phẩm thành công");
            result.put("data", comment);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Thêm sản phẩm thất bại!!!", e);
            result.put("success", false);
            result.put("message", "Thêm sản phẩm thất bại");
            result.put("data", null);
            return ResponseEntity.internalServerError().body(result);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable int id, @RequestBody CommentDTO commentDTO) {
        Map<String, Object> result = new HashMap<>();
        try {
            Optional<CommentDTO> updatedComment = commentService.updateComment(id, commentDTO);
            if (updatedComment.isPresent()) {
                result.put("success", true);
                result.put("message", "Call API thành công");
                result.put("data", updatedComment.get());
            } else {
                result.put("success", false);
                result.put("message", "Product không tồn tại");
                result.put("data", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        }catch (Exception e){
            log.error("Call api thất bại!!!",e);
            result.put("success",false);
            result.put("message","Call api thất bại");
            result.put("data",null);
            return ResponseEntity.internalServerError().body(result);
        }
        return ResponseEntity.ok(result);
    }
}
