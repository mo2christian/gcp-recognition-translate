package com.mo2christian.recognition.translate.controller;

import com.mo2christian.recognition.translate.service.PublishService;
import com.mo2christian.recognition.translate.service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller pour la traduction.
 */
@Controller
public class TranslateController {

    private TranslateService translateService;

    private PublishService publishService;

    @Autowired
    public void setTranslateService(TranslateService translateService) {
        this.translateService = translateService;
    }

    @Autowired
    public void setPublishService(PublishService publishService) {
        this.publishService = publishService;
    }

    /**
     * URL de ping, pour verifier la disponibilité du service.
     * @return
     */
    @RequestMapping({"/", "/_ah/start"})
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("OK");
    }


    /**
     * URL de traduction.
     * @param request
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Response translate(@RequestBody Request request){
        // traduire les texts
        Response response = new Response();
        response.getWords().addAll(translateService.translate(request.getFrom(), request.getTarget(), request.getWords()));
        response.setLanguage(request.getTarget());
        //publier les textes traduits
        StringBuilder builder = new StringBuilder();
        builder.append(request.getWords().get(0));
        for (int i = 1; i < request.getWords().size(); i++){
            builder.append(":" + request.getWords().get(i));
        }
        publishService.publish(builder.toString());
        return response;
    }

}
