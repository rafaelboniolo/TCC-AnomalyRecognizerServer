package com.tcc.recognizer.commom;

import java.util.List;

import com.tcc.recognizer.model.ResultModel;

public class ResponseReplacer {

    public static ResultModel singleReplace(String response){
        
        ResultModel rm = new ResultModel();

        String [] responses = response
            .replace("[", "")
            .replace("]", "")
            .split(",");

        rm.setPredictedClass(responses[0]);
        rm.setImage(responses[1]);
        // rm.setTime(time);

        return rm;
    }

    public static List<ResultModel> multipleReplace(){
        return null;
    }
    
}