package org.loose.fis.registration.example.services;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.io.FileUtils;
import org.loose.fis.registration.example.exceptions.CouldNotWriteUsersException;
import org.loose.fis.registration.example.exceptions.IncorrectLogin;
import org.loose.fis.registration.example.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.registration.example.model.User;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

public class UserService {

    private static ObjectRepository<User> userRepository;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "users.json");

    private static ObjectRepository<User> users;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(FileSystemService.getPathToFile("ceva.db").toFile())
                .openOrCreate("ceva", "ceva");

        users = database.getRepository(User.class);
    }

    /*
    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("users.json"), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
    }
    */

    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        users.insert(new User(username, encodePassword(username, password), role));
        persistUsers();
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : users.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    public static String getRoleByUsername(String username) throws IncorrectLogin{
        String role= new String();
        int ok=0;
        for(User user:userRepository.find()){
            if(Objects.equals(user.getUsername(),username)){
                role=user.getRole();
                ok=1;}
        }
        if(ok==0)
            throw new IncorrectLogin(username);

        return role;
    }

    public static int checkUserLogin(String username, String password, String role) throws IncorrectLogin
    {
        String pass=encodePassword(username,password);
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()) && Objects.equals(role,user.getRole()) && pass.equals(user.getPassword()))
            {
                if(role.equals("Client"))
                {
                    return 1;
                }
                else
                {
                    return 2;
                }
            }
        }
        throw new IncorrectLogin(username);
    }

    private static void persistUsers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }


}
