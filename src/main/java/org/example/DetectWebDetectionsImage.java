package org.example;

import com.google.cloud.vision.v1.*;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.protobuf.ByteString;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetectWebDetectionsImage {


    // Finds references to the specified image on the web.
    public static String detectWebDetections(MultipartFile file) throws IOException {
        List<AnnotateImageRequest> requests = new ArrayList<>();
        StringBuilder results = new StringBuilder();
        ByteString imgBytes = ByteString.copyFrom(file.getBytes());

        Image img = Image.newBuilder().setContent(imgBytes).build();
        Feature feat = Feature.newBuilder().setType(Type.WEB_DETECTION).build();
        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
        requests.add(request);

        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests. After completing all of your requests, call
        // the "close" method on the client to safely clean up any remaining background resources.
        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    System.out.format("Error: %s%n", res.getError().getMessage());
                    return "Error: " + res.getError().getMessage();
                }

                // Search the web for usages of the image. You could use these signals later
                // for user input moderation or linking external references.
                // For a full list of available annotations, see http://g.co/cloud/vision/docs
                WebDetection annotation = res.getWebDetection();
                results.append("Entity:Id:Score\n==============\n");
                annotation.getWebEntitiesList().forEach(entity ->
                        results.append(entity.getDescription()).append(" : ")
                                .append(entity.getEntityId()).append(" : ")
                                .append(entity.getScore()).append("\n"));
                annotation.getBestGuessLabelsList().forEach(label ->
                        results.append("\nBest guess label: ").append(label.getLabel()).append("\n"));
                results.append("\nPages with matching images: Score\n==\n");
                annotation.getPagesWithMatchingImagesList().forEach(page ->
                        results.append(page.getUrl()).append(" : ").append(page.getScore()).append("\n"));
                results.append("\nPages with partially matching images: Score\n==\n");
                annotation.getPartialMatchingImagesList().forEach(image ->
                        results.append(image.getUrl()).append(" : ").append(image.getScore()).append("\n"));
                results.append("\nPages with fully matching images: Score\n==\n");
                annotation.getFullMatchingImagesList().forEach(image ->
                        results.append(image.getUrl()).append(" : ").append(image.getScore()).append("\n"));
                results.append("\nPages with visually similar images: Score\n==\n");
                annotation.getVisuallySimilarImagesList().forEach(image ->
                        results.append(image.getUrl()).append(" : ").append(image.getScore()).append("\n"));
            }
            return results.toString();
            }
        }
    }
