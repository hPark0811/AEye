package com.heung.mobileapp.service;

import com.ibm.cloud.sdk.core.http.HttpConfigOptions;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.cloud.sdk.core.service.BaseService;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import com.ibm.watson.visual_recognition.v4.VisualRecognition;
import com.ibm.watson.visual_recognition.v4.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;


public class APIManager extends BaseService {

    static final String URL = "https://gateway.watsonplatform.net/visual-recognition/api";
    static final String APIKEY = "rMH9n5izEvO6bAdqZU4LVgEVfSn6cQhRsp81BR1ZWUNI";
    static final String VERSION = "2019-02-11";
    static final String MODEL_ID = "80a477c6-dd60-4ecd-b496-bab7e8f6c8d7";

    public static Map<String, String> getVisualRecognitionData(String imagePath) throws FileNotFoundException {
        System.out.println("LOLOLOL");
        IamAuthenticator authenticator = new IamAuthenticator(APIKEY);
        VisualRecognition visualRecognition = new VisualRecognition(VERSION, authenticator);
        visualRecognition.setServiceUrl(URL);

        HttpConfigOptions configOptions = new HttpConfigOptions.Builder()
                .disableSslVerification(true)
                .build();
        visualRecognition.configureClient(configOptions);

        FileWithMetadata fileWithMetadata = new FileWithMetadata.Builder().data(
                new File(imagePath))
                .contentType(AnalyzeOptions.Features.OBJECTS)
                .build();

        AnalyzeOptions options = new AnalyzeOptions.Builder()
                .addImagesFile(fileWithMetadata)
                .addCollectionIds(MODEL_ID)
                .addFeatures(AnalyzeOptions.Features.OBJECTS)
                .build();

        AnalyzeResponse response = visualRecognition.analyze(options).execute().getResult();
        System.out.println(response);
        return null;
    }
}

