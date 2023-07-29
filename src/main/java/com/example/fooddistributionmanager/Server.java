package com.example.fooddistributionmanager;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static String returnData;
    
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2222);
        System.out.println("Server started");

        String email;
        String password;


        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("Server Connected");

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());


            try {
                Object checkUser = ois.readObject();
                String line = (String) checkUser;
                String[] parts = line.split("&%&");
                email = parts[0];
                password  = parts[1];
                String sMsg = checkData(email,password);
                oos.writeObject(sMsg);
                oos.flush();
                System.out.println(sMsg);
                if (sMsg.equals("admin") || sMsg.equals("worker")) {
                    oos.writeObject(returnData);
                    oos.flush();
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        
    }

    private static String checkData(String email, String password) {
        List<User> list = new ArrayList<>();
        User user;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Data/users.txt"));
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split("&%&");
                user = new User();
                user.setEmail(parts[0]);
                user.setPassword(parts[1]);
                user.setName(parts[2]);
                user.setAccount(parts[3]);
                user.setPhone(parts[4]);
                list.add(user);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String message = null;

        for (User s: list) {
            if ((s.getEmail().equals(email)) && (s.getPassword().equals(password))) {
                if (s.getAccount().equals("admin")) {
                    message = "admin";
                    returnData = sendUser(s);
                    break;
                } else if (s.getAccount().equals("worker")) {
                    message = "worker";
                    returnData = sendUser(s);
                    break;
                }
            } else {
                message = "Wrong user or Password";
            }
        }
        return message;

    }

    private static String sendUser(User u) {
        String data = u.getEmail()+"&%&" + u.getPassword() + "&%&" + u.getName() + "&%&" + u.getAccount() + "&%&" + u.getPhone();
        return data;
    }

}
