package com.musicbox.music.controllers;

//import com.microsoft.azure.storage.CloudStorageAccount;
//import com.microsoft.azure.storage.OperationContext;
//import com.microsoft.azure.storage.StorageException;
//import com.microsoft.azure.storage.blob.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.WritableResource;
//import org.springframework.util.StreamUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.nio.charset.Charset;
//import java.security.InvalidKeyException;

//@RestController
public class WebController {
//    @Value("blob://music/myfile.txt")
//    private Resource blobFile;
//
//    @Value("DefaultEndpointsProtocol=https;EndpointSuffix=core.windows.net;AccountName=musicbox;AccountKey=rIH9psFsN35FfSAtt6f87m0owzJtehEpjNcXKSOnuFStO2DQRASsKdZkzhMJx2KUgwstxB8JxwxQ6T+xsOaOKw==")
//    private String storageConnectionString;
//
//    public void connect() throws URISyntaxException, StorageException, InvalidKeyException {
//        // Parse the connection string and create a blob client to interact with Blob storage
//        CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
//        CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
//        CloudBlobContainer container = blobClient.getContainerReference("musicbox");
//
//        // Create the container if it does not exist with public access.
//        container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());
//    }
//
////    @ResponseBody
//    @GetMapping(value = "/")
//    public String readBlobFile() throws IOException {
//        return StreamUtils.copyToString(
//                this.blobFile.getInputStream(),
//                Charset.defaultCharset()) + "\n";
//    }
//
////    @ResponseBody
//    @PostMapping(value = "/")
//    public String writeBlobFile(@RequestBody String data) throws IOException {
//        CloudBlobClient blobClient = new CloudBlobClient(new URI("https://storagetest789.blob.core.windows.net/"), credentialsToken);
//        CloudBlobContainer blobContainer = blobClient.getContainerReference("pub");
//        CloudBlockBlob blockBlob = blobContainer.getBlockBlobReference("test.txt");
//        blockBlob.uploadText("Test!");
//        try (OutputStream os = ((WritableResource) this.blobFile).getOutputStream()) {
//            os.write(data.getBytes());
//        }
//        return "File was updated.\n";
//    }
}
