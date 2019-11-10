package com.pra.backup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
public class ObjectSerializer {

	private Class<?> objectClass;
	private Object object;
	private String opType;

	synchronized public void serialize(File backupFile) throws IOException {
		String backupString = new ObjectMapper().writeValueAsString(this);
		FileWriter writer = new FileWriter(backupFile,true);
		writer.append(backupString+"\n");
		writer.close();
		log.info("backed Up : "+backupString);
	}

	public static ObjectSerializer deserialize(String objectSerializer) throws IOException {
		ObjectSerializer restored = new ObjectMapper().readerFor(ObjectSerializer.class).readValue(objectSerializer);

		log.info(restored.toString());
	
		restored.setObject(new ObjectMapper().readerFor(restored.getObjectClass())
				.readValue(new ObjectMapper().writeValueAsString(restored.getObject())));

		return restored;
	}

}
