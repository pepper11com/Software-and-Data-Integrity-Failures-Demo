package org.example.server;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;
import org.example.server.serialize.HackObject;
import org.example.server.serialize.SimpleObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) throws IOException {
		HackObject obj = new HackObject();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(baos);
		out.writeObject(obj);
		out.close();
		String base64Encoded = Base64.getEncoder().encodeToString(baos.toByteArray());
		System.out.println(base64Encoded);



		SpringApplication.run(ServerApplication.class, args);
	}

}
