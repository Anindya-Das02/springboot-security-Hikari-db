package das.spring.security.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class BaseController {

    @GetMapping(value = {"/","index"})
    @ResponseBody
    public Map<String,String> index(){
        return Map.of("message","Index page");
    }

    @GetMapping("/admin")
    @ResponseBody
    public ResponseEntity<?> adminPage(){
        return ResponseEntity.ok(Map.of("message","welcome to Admin Page","status",200));
    }

    @GetMapping("/public")
    @ResponseBody
    public ResponseEntity<?> publicPage(){
        return ResponseEntity.ok(Map.of("message","This is Public Page","status",200));
    }

    @GetMapping("/sup")
    @ResponseBody
    public ResponseEntity<?> supervisorPage(){
        return ResponseEntity.ok(Map.of("message","This is Supervisor Page","status",200));
    }
}
