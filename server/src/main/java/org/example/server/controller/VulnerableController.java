package org.example.server.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;

@RestController
public class VulnerableController {

    @CrossOrigin
    @PostMapping("/vulnerable/deserialize")
    public String deserialize(@RequestBody String base64Data) {
        try {
            byte[] data = Base64.getDecoder().decode(base64Data);
            try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
                Object obj = ois.readObject();
                return "Deserialized object: " + obj.toString();
            }
        } catch (Exception e) {
            return "Deserialization failed: " + e.getMessage();
        }
    }
}
