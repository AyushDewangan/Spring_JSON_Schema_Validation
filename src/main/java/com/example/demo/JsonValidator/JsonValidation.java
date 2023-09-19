package com.example.demo.JsonValidator;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.*;

public class JsonValidation {

	public void checkValidation(String payloadJson) {

		ObjectMapper objectMapper = new ObjectMapper();
		JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909);

		try (InputStream jsonStream = new ByteArrayInputStream(payloadJson.getBytes());
				InputStream schemaStream = new FileInputStream("TaskValidation_Schema.json");) {
			JsonNode json = objectMapper.readTree(jsonStream);

			JsonSchema schema = schemaFactory.getSchema(schemaStream);
			Set<ValidationMessage> validationResult = schema.validate(json);

			// print validation errors
			if (validationResult.isEmpty()) {
				System.out.println("no validation errors :-)");
			} else {
				validationResult.forEach(vm -> System.out.println(vm.getMessage()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
