package test1;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Loader extends ClassLoader{
	private static JarInputStream zis;
	private static Map<String, ByteBuffer> entryMap;
	public static final void dencryptJar(String file){
		try{
		    zis = new JarInputStream (new FileInputStream(file));
		    entryMap = new HashMap<String, ByteBuffer>();
		    JarEntry je = null;
		   
			
			while ((je = zis.getNextJarEntry()) !=null) {
				String name = je.getName();
				if(name.endsWith(".class")){
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					int bread,len;
					for(len=0;((bread = zis.read())!= -1);len++){
						bos.write(bread);
					}
					byte[] Original_data = bos.toByteArray();
					//System.out.println("Dencrypt:"+denJarString);	
					ByteBuffer buffer = ByteBuffer.wrap(Original_data );
				    //jos.write(denJar, 0, denJar.length);
					entryMap.put(name, buffer); 
				  }else {
					  ByteArrayOutputStream bos = new ByteArrayOutputStream();
						int bread,len;
						for(len=0;((bread = zis.read())!= -1);len++){
							bos.write(bread);
						}
						byte[] bytes = bos.toByteArray();
						 ByteBuffer buffer = ByteBuffer.wrap(bytes);
						 //jos.write(bytes, 0, bytes.length);
						 entryMap.put(name, buffer); 
				  }
				//jos.flush();
			}
	
				
			//jos.close();
			zis.close();
		}catch(Exception localException ){
			
		}
		
	}
	
	protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = name.replace('.', '/').concat(".class");
        ByteBuffer buffer = entryMap.get(path);
        if(buffer == null) {
            return super.findClass(name);
        } else {
            byte[] bytes = buffer.array();
            return defineClass(name, bytes, 0, bytes.length);
        }
    }
	/**
     * 关闭Decoder
     * @throws IOException
     */
    public void close() throws IOException {
        Iterator<ByteBuffer> iterator = entryMap.values().iterator();
        while(iterator.hasNext()) {
            ByteBuffer buffer = iterator.next();
            buffer.clear(); //清空ByteBuffer对象缓存
        }
        entryMap.clear(); //清空HashMap
    }
}
