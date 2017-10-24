/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.appprojeto;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Properties;

/**
 *
 * @author laerton
 */
public class FileGoogleDrive 
{
    /**
   * Be sure to specify the name of your application. If the application name is {@code null} or
   * blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
   */
  private static final String APPLICATION_NAME = "ProjetoPOD";

  private static final String UPLOAD_FILE_PATH = "teste.xml";
  private static final String DIR_FOR_DOWNLOADS = "";
  private static final java.io.File UPLOAD_FILE = new java.io.File(UPLOAD_FILE_PATH);

  /** Directory to store user credentials. */
  private static final java.io.File DATA_STORE_DIR =
      new java.io.File("");//System.getProperty("user.home"), ".store/drive_sample");

  /**
   * Global instance of the {@link DataStoreFactory}. The best practice is to make it a single
   * globally shared instance across your application.
   */
  private static FileDataStoreFactory dataStoreFactory;

  /** Global instance of the HTTP transport. */
  private static HttpTransport httpTransport;

  /** Global instance of the JSON factory. */
  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

  /** Global Drive API client. */
  private static Drive drive;

  /** Authorizes the installed application to access user's protected data. */
  private static Credential authorize() throws Exception {
    // load client secrets
   // GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
   //    new InputStreamReader(FileGoogleDrive.class.getResourceAsStream("client_id.json")));
        
        java.io.File file = new java.io.File("client_id.json");
        InputStreamReader isr = new FileReader(file);
        Properties properties = new Properties();
        //File config = new File(System.getProperty("user.home").
        //        concat(File.separator).concat("client_id.jason"));
        properties.load(new FileReader(file));
        GoogleClientSecrets secrets  = new GoogleClientSecrets();
        
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,isr);
        GoogleClientSecrets.Details detalhes = new GoogleClientSecrets.Details();
    if (clientSecrets.getDetails().getClientId().startsWith("Enter")
        || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
      System.out.println(
          "Enter Client ID and Secret from https://code.google.com/apis/console/?api=drive "
          + "into drive-cmdline-sample/src/main/resources/client_secrets.json");
      System.exit(1);
    }
    // set up authorization code flow
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        httpTransport, JSON_FACTORY, clientSecrets,
        Collections.singleton(DriveScopes.DRIVE_FILE)).setDataStoreFactory(dataStoreFactory)
        .build();
    // authorize
    return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
  }

  public static void main(String[] args) {
    Preconditions.checkArgument(
        !UPLOAD_FILE_PATH.startsWith("Enter ") && !DIR_FOR_DOWNLOADS.startsWith("Enter "),
        "Please enter the upload file path and download directory in %s", FileGoogleDrive.class);

    try {
      httpTransport = GoogleNetHttpTransport.newTrustedTransport();
      dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
      // authorization
      Credential credential = authorize();
      // set up the global Drive instance
      drive = new Drive.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(
          APPLICATION_NAME).build();

      // run commands

      System.err.println(("Starting Resumable Media Upload"));
      File uploadedFile = uploadFile(false);

      System.err.println(("Updating Uploaded File Name"));
      File updatedFile = updateFileWithTestSuffix(uploadedFile.getId());

      System.err.println(("Starting Resumable Media Download"));
      downloadFile(false, updatedFile);
        
      System.err.println(("Starting Simple Media Upload"));
      uploadedFile = uploadFile(true);

      System.err.println(("Starting Simple Media Download"));
      downloadFile(true, uploadedFile);

      System.err.println(("Success!"));
      return;
    } catch (IOException e) {
      System.err.println(e.getMessage());
    } catch (Throwable t) {
      t.printStackTrace();
    }
    System.exit(1);
  }

  /** Uploads a file using either resumable or direct media upload. */
  private static File uploadFile(boolean useDirectUpload) throws IOException {
    File fileMetadata = new File();
    fileMetadata.setTitle(UPLOAD_FILE.getName());

    FileContent mediaContent = new FileContent("image/jpeg", UPLOAD_FILE);

    Drive.Files.Insert insert = drive.files().insert(fileMetadata, mediaContent);
    MediaHttpUploader uploader = insert.getMediaHttpUploader();
    uploader.setDirectUploadEnabled(useDirectUpload);
    //uploader.setProgressListener(new FileUploadProgressListener());
    return insert.execute();
  }

  /** Updates the name of the uploaded file to have a "drivetest-" prefix. */
  private static File updateFileWithTestSuffix(String id) throws IOException {
    File fileMetadata = new File();
    fileMetadata.setTitle("drivetest-" + UPLOAD_FILE.getName());

    Drive.Files.Update update = drive.files().update(id, fileMetadata);
    return update.execute();
  }

  /** Downloads a file using either resumable or direct media download. */
  private static void downloadFile(boolean useDirectDownload, File uploadedFile)
      throws IOException {
    // create parent directory (if necessary)
    java.io.File parentDir = new java.io.File(DIR_FOR_DOWNLOADS);
    if (!parentDir.exists() && !parentDir.mkdirs()) {
      throw new IOException("Unable to create parent directory");
    }
    OutputStream out = new FileOutputStream(new java.io.File(parentDir, uploadedFile.getTitle()));

    MediaHttpDownloader downloader =
        new MediaHttpDownloader(httpTransport, drive.getRequestFactory().getInitializer());
    downloader.setDirectDownloadEnabled(useDirectDownload);
    //downloader.setProgressListener(new FileDownloadProgressListener());
    downloader.download(new GenericUrl(uploadedFile.getDownloadUrl()), out);
  }
    
}
