package com.sahariar.InventoryKnitGarden.helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadHelper {

    public void saveUploadedFiles(List<MultipartFile> files, String uploadFolder) throws IOException {

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFolder + file.getOriginalFilename());
            System.out.println(path.toString());
            Files.write(path, bytes);

        }

    }
    public byte[] getByteArrayFromInputStream(InputStream is)
	{      
		        
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				
				int nRead;
				byte[] data = new byte[16384];

				try {
					System.out.println("available is "+is.available()); 
					while ((nRead = is.read(data, 0, data.length)) != -1) {
					  buffer.write(data, 0, nRead);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		return buffer.toByteArray();
	}
	
}
