package org.example.server.controller;

import org.example.server.serialize.SimpleObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputFilter;
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
                SimpleObject obj = (SimpleObject)ois.readObject();
                System.out.println(obj);
                return "Deserialized object: " + obj.getMessage();
            }
        } catch (Exception e) {
            return "Deserialization failed: " + e.getMessage();
        }
    }

    @CrossOrigin
    @PostMapping("/safe/deserialize")
    public String deserializeSafe(@RequestBody String base64Data) {
        try {
            byte[] data = Base64.getDecoder().decode(base64Data);

            ObjectInputFilter filter = ObjectInputFilter.Config.createFilter("org.example.server.serialize.SimpleObject;!*");
            try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
                ois.setObjectInputFilter(filter);
                SimpleObject obj = (SimpleObject)ois.readObject();

                return "Deserialized object: " + (obj).getMessage();
            }
        } catch (Exception e) {
            return "Deserialization failed: " + e.getMessage();
        }
    }
}
