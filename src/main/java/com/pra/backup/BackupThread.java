package com.pra.backup;

import java.io.File;
import java.io.IOException;

import com.pra.controller.BackupController;

public class BackupThread extends Thread{
	
	public static final String SAVE = "SAVE";
	public static final String UPDATE = "UPDATE";
	public static final String DELETE = "DELETE";
	private static File backupFile;
	
	private Class<?> objectClass;
	private Object object;
	private String opType;
	
	private BackupThread(Class<?> clazz, Object obj, String opType) {
		this.objectClass = clazz;
		this.object = obj;
		this.opType = opType;
	}
	
	public static void refreshBackupPath(String path) {
		backupFile = new File(path);
	}
	
	public static void backup( Class<?> clazz, Object obj, String opType) {
		if(backupFile == null) {
			backupFile = new File(BackupController.BACKUP_PATH);
		}
		BackupThread bt = new BackupThread(clazz, obj, opType);
		bt.start();
	}
	
	@Override
	public void run() {
		ObjectSerializer obj = new ObjectSerializer(this.objectClass,this.object,this.opType );
		try {
			obj.serialize(backupFile);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
