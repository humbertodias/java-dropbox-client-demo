package com.mycompany.app;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

import java.util.*;
import java.io.*;

import java.text.*;
import java.util.*;


public class App {

    public static void main(String ... args) throws DbxException, IOException {

    	if(args.length < 1) throw new RuntimeException("Missing AccessToken!");
    	
    	String ACCESS_TOKEN = args[0];

        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        // Get current account info
        FullAccount account = client.users().getCurrentAccount();
        System.out.printf("Account Name: %s\n", account.getName().getDisplayName());

        System.out.println("[List Files]");
        // Get files and folder metadata from Dropbox root directory
        ListFolderResult result = client.files().listFolder("");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd.HH.mm.ss");
        String sdt = sdf.format( new Date() );

		File tempFile = File.createTempFile("temp-upload-file-" + sdt, ".txt");
		try(PrintWriter writer = new PrintWriter(new FileWriter(tempFile)) ){
	        writer.println(sdt);
	    }

        // Upload "test.txt" to Dropbox
        try (InputStream in = new FileInputStream(tempFile)) {
            FileMetadata metadata = client.files().uploadBuilder("/" + tempFile.getName() )
                .uploadAndFinish(in);
        }

        tempFile.deleteOnExit();
    }
}