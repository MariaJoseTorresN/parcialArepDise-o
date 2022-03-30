package edu.escuelaing.arep;
import static spark.Spark.*;


import org.json.*;

import spark.Request;
import spark.Response;

public class SparkWebApp {
    private static Calculator servicesCal = new Calculator();
    public static void main(String[] args) {
        port(getPort());
        get("/hello",(req,res)->{
            return "Hello :)";
        });
        get("/log",SparkWebApp::getLog);
        get("/acos",SparkWebApp::getAcos);
    }
    static int getPort(){
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
    private static Object getLog(Request req, Response res){
        double calculo = servicesCal.calculatorLog(Double.parseDouble(req.queryParams("value")));
        JSONObject myObject = new JSONObject();
        myObject.put("operation","Log");
        myObject.put("input",req.queryParams("value"));
        myObject.put("output",calculo);
        return myObject;
    }
    private static Object getAcos(Request req, Response res){
        double calculo = servicesCal.calculatorAcos(Double.parseDouble(req.queryParams("value")));
        JSONObject myObject = new JSONObject();
        myObject.put("operation","Acos");
        myObject.put("input",req.queryParams("value"));
        myObject.put("output",calculo);
        return myObject;
    }
}
