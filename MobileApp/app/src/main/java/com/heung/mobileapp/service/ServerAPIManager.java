package com.heung.mobileapp.service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class ServerAPIManager {

    static final String URL = "http://23.20.70.2:8080";

    public static void addUser(String email, String pwd, String phone){
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    java.net.URL url = new URL(URL + "/user");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/json");

                    JSONObject json = new JSONObject();
                    json.put("email", email);
                    json.put("password", pwd);
                    json.put("phone", phone);

                    OutputStream os = con.getOutputStream();
                    os.write(json.toString().getBytes());
                    os.flush();

                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String output;
                    System.out.println("Output from Server .... \n");
                    while ((output = br.readLine()) != null) {
                        System.out.println(output);
                    }
                    con.disconnect();

                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        thread.start();

    }

    public static void addAssociate(String userId, String name, String relationship, String phone){
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    java.net.URL url = new URL(URL + "/associate");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/json");

                    JSONObject json = new JSONObject();
                    json.put("associateUser", userId);
                    json.put("name", name);
                    json.put("relationship", relationship);
                    json.put("phone", phone);

                    OutputStream os = con.getOutputStream();
                    os.write(json.toString().getBytes());
                    os.flush();

                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String output;
                    System.out.println("Output from Server .... \n");
                    while ((output = br.readLine()) != null) {
                        System.out.println(output);
                    }
                    con.disconnect();

                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        thread.start();
    }
    public static Map<String, String> getUserID(String email) throws Exception{
        try{
            StringBuilder result = new StringBuilder();
            URL url = new URL(URL + "/user/" + email);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(1000);
            InputStream in = new BufferedInputStream(con.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            JSONArray jsonArray = new JSONArray(String.valueOf(result));
            if (jsonArray.length() > 0){
                Map<String, String> recMap = new HashMap<>();
                recMap.put("_id", ((JSONObject)jsonArray.get(0)).get("_id").toString());
                return recMap;
            } else {
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Cant add associate");
        }
        
        return null;
    }
    
        public static void sendTextMessageToAll() throws Exception{
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    java.net.URL url = new URL(URL + "/sendTextMessageToAll");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/json");

                    JSONObject json = new JSONObject();
                    json.put("email", CreateUserActivity.getEmail());
                    json.put("Body", "User "+CreateUserActivity.getEmail() + "is asking for help!");

                    OutputStream os = con.getOutputStream();
                    os.write(json.toString().getBytes());
                    os.flush();

                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String output;
                    System.out.println("Output from Server .... \n");
                    while ((output = br.readLine()) != null) {
                        System.out.println(output);
                    }
                    con.disconnect();

                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public static void sendCallMessageToAll() throws Exception{
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    java.net.URL url = new URL(URL + "/sendCallToAll");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/json");

                    JSONObject json = new JSONObject();
                    json.put("email", CreateUserActivity.getEmail());
                    json.put("Text", "User"+ CreateUserActivity.getEmail() + "IsAskingForHelp!");

                    OutputStream os = con.getOutputStream();
                    os.write(json.toString().getBytes());
                    os.flush();

                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String output;
                    System.out.println("Output from Server .... \n");
                    while ((output = br.readLine()) != null) {
                        System.out.println(output);
                    }
                    con.disconnect();

                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        thread.start();
    }
}