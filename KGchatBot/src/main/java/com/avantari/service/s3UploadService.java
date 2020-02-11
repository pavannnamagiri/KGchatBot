package com.avantari.service;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class s3UploadService {

	public void s3Upload(FileItem item, String filename, InputStream ios) {

		try {
			AWSCredentials credentials = new BasicAWSCredentials("<<access key>>",
					"<<security code>>");
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion("region").build();
			String bucketName = "mychatbotbucketpawan";

			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(item.getSize());
			// meta.setContentType("pdf/pdf");

			System.out.println();
			s3Client.putObject(new PutObjectRequest(bucketName, filename, ios, meta));
		} catch (AmazonS3Exception awse) {
			System.out.println(awse);

		} catch (AmazonServiceException e) {

			e.printStackTrace();
		} catch (SdkClientException e) {
			// Amazon S3 couldn't be contacted for a response, or the client
			// couldn't parse the response from Amazon S3.
			e.printStackTrace();
		}

	}
}
