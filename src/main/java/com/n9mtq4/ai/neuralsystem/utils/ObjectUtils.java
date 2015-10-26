package com.n9mtq4.ai.neuralsystem.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by will on 4/28/15 at 6:52 PM.
 */
public class ObjectUtils {
	
	/**
	 * Read a saved serializable object.
	 *
	 * @param <E>  the type parameter
	 * @param file the file
	 * @return the object that has been saved
	 * @throws Exception any errors that may have occurred.
	 */
	public static <E> E readSerializable(File file) throws Exception {
		FileInputStream fileInputStream = new FileInputStream(file);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		E o = (E) objectInputStream.readObject();
		objectInputStream.close();
		return o;
	}
	
	/**
	 * Read serializable object.
	 *
	 * @param file the file
	 * @return the object
	 * @throws Exception any errors that may have occurred.
	 */
	@Deprecated
	public static Object readSerializableObject(File file) throws Exception {
		FileInputStream fileInputStream = new FileInputStream(file);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		Object o = objectInputStream.readObject();
		objectInputStream.close();
		return o;
	}
	
	/**
	 * Write a serializable to a file.
	 *
	 * @param object the object
	 * @param file   the file
	 * @throws Exception any errors that may have occur.
	 */
	public static void writeSerializable(Object object, File file) throws Exception {
		
		if (!(object instanceof Serializable))
			throw new IllegalArgumentException("The object must be Serializable");
		
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(object);
		objectOutputStream.close();
		
	}
	
}
