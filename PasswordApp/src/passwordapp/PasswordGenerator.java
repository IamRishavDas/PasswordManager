/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package passwordapp;

import java.security.SecureRandom;

/**
 *
 * @author Rishav
 */
class LengthLessThanFourException extends Exception{
    @Override
    public String toString(){
        return "Length Must Be Greator Than 4!!";
    }
}

public class PasswordGenerator {
    
    private final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private final String NUMBERS = "1234567890";
    private final String SPECIAL_CHARACTERS = "~!@#$%^&*(){}[]-_/\\|?<>";
    
    private StringBuffer password;
    private SecureRandom random;
    
    private static char getRandomCharacter(String str, SecureRandom random){
        int randomIndex = random.nextInt(str.length());
        return str.charAt(randomIndex);
    }
    
    public String getPassword(int length) throws LengthLessThanFourException{
        
        if(length<4)
            throw new LengthLessThanFourException();
        else{
            password = new StringBuffer();
            random = new SecureRandom();
        
            password.append(getRandomCharacter(UPPER_CASE, random));
            password.append(getRandomCharacter(LOWER_CASE, random));
            password.append(getRandomCharacter(NUMBERS, random));
            password.append(getRandomCharacter(SPECIAL_CHARACTERS, random));
        
            String allCharacters = UPPER_CASE + LOWER_CASE + NUMBERS + SPECIAL_CHARACTERS;
        
            for(int i=4;i<length;i++){
            password.append(getRandomCharacter(allCharacters, random));
            }
        
            char[] passwordArray = password.toString().toCharArray();
            for(int i=0;i<length;i++){
            int randomIndex = random.nextInt(passwordArray.length);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[randomIndex];
            passwordArray[randomIndex] = temp;
            }
            
            // if the last symbol is backslash
            if(passwordArray[length-1] == '\\'){
                int index = random.nextInt(length);
                
                char temp = passwordArray[length-1];
                passwordArray[length-1] = passwordArray[index];
                passwordArray[index] = temp;
            }
        
            return new String(passwordArray);
        }
    }
}
