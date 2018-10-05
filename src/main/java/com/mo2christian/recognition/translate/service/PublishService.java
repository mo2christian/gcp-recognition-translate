package com.mo2christian.recognition.translate.service;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.rpc.ApiException;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Service pour publier les messages
 */
@Service
public class PublishService {

    private Publisher publisher;

    private final Logger logger = Logger.getLogger("translate");

    @PostConstruct
    public void construct() throws IOException {
        logger.info("Initializing publisher");
        ProjectTopicName topicName = ProjectTopicName.of(System.getenv("GOOGLE_CLOUD_PROJECT"), "translate");
        publisher = Publisher.newBuilder(topicName).build();
    }

    @PreDestroy
    public void destroy() throws Exception{
        logger.info("Shutdown publisher");
        publisher.shutdown();
    }

    /**
     * Publie le message.
     * @param message
     */
    public void publish(String message){
        ByteString data = ByteString.copyFromUtf8(message);
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();
        ApiFuture<String> future = publisher.publish(pubsubMessage);
        ApiFutures.addCallback(future, new ApiFutureCallback<String>() {

            @Override
            public void onFailure(Throwable throwable) {
                logger.severe("Error publishing message : " + message);
            }

            @Override
            public void onSuccess(String messageId) {
                logger.info("Message published : ID = " + messageId);
            }
        });
    }
}
