package com.avantari.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@Controller
public class AwsFileListController {

	@ResponseBody
	@RequestMapping(value = "test/s3List")
	public ArrayList<String> listObjects() {
		ArrayList<String> bucketObjList = new ArrayList<String>();

		AWSCredentials credentials = new BasicAWSCredentials("<<security key>>",
				"<<access key>>");
		Regions clientRegion = Regions.DEFAULT_REGION;
		String bucketName = "mychatbotbucketpawan";
		// String key = "*** Object key ***";SS

		S3Object fullObject = null, objectPortion = null, headerOverrideObject = null;
		try {
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion("us-east-1")
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

			// Get an object and print its contents.
			System.out.println("Listing objects");

			// maxKeys is set to 2 to demonstrate the use of
			// ListObjectsV2Result.getNextContinuationToken()
			ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName).withMaxKeys(2);
			ListObjectsV2Result result;

			do {
				result = s3Client.listObjectsV2(req);

				for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
					System.out.printf(" - %s (size: %d)\n", objectSummary.getKey(), objectSummary.getSize());
					bucketObjList.add(objectSummary.getKey());
				}
				// If there are more than maxKeys keys in the bucket, get a continuation token
				// and list the next objects.
				String token = result.getNextContinuationToken();
				System.out.println("Next Continuation Token: " + token);
				req.setContinuationToken(token);
			} while (result.isTruncated());
		} catch (AmazonServiceException e) {
			// The call was transmitted successfully, but Amazon S3 couldn't process
			// it, so it returned an error response.
			e.printStackTrace();
		} catch (SdkClientException e) {
			// Amazon S3 couldn't be contacted for a response, or the client
			// couldn't parse the response from Amazon S3.
			e.printStackTrace();
		}

		return bucketObjList;

	}
}
