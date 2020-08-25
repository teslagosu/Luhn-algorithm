package Controller;

import View.Message;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Luhn {
    Message message = new Message();
    //checks if pnr is valid or not
    public void checkSsn(String ssn) {

        if (checkSsnLength(ssn) == false) {
            message.print("Enter 10 digit social security number, ex: 8101012233");
        } else {
            //control number as: 990101-014[5]
            String controlCheck = ssn.substring(9, 10);
            //check number for gender control later on.
            int genderCheckNumber = Integer.parseInt(ssn.substring(8,9));
            message.print(genderCheckNumber);
            int validControlNumber;
            //store integers temporary in the arraylist
            ArrayList<Integer> temp = calculateIndexes(ssn);
            //converts arraylist to a string
            String getStringFromArray = convertIntegersInArrayListToString(temp);
            //gets the sum of all numbers in the string.
            int sumOfString = calculateSum(getStringFromArray);
            //if the sum is ex: 30,40,50. Ends with 0 then check if modulos 10 == 0.
            if (sumOfString % 10 == 0) {
                //set validControlNumber to 0
                validControlNumber = 0;
            } else {
                //else round up current numbers ex: 42,55,68,99 to closest integer of 10, ex: 50,60,70,100
                int tenAboveSum = ((sumOfString / 10) * 10) + 10;
                //get the valid control number
                validControlNumber = tenAboveSum - sumOfString;
            }
            //check if the control number is equal to the control number entered in the beginning.
            if (controlCheck.equals(Integer.toString(validControlNumber))) {
                message.print("Valid Social Security Number");
                if(checkGender(genderCheckNumber) == 0){
                    message.print("Female");
                }
                else if(checkGender(genderCheckNumber) == 1){
                    message.print("Male");
                }
            } else {
                message.print("Not a valid Social Security Number");
            }
        }
    }

    public int checkGender(int number){
        if(number % 2 == 0){
            return 0;
        }else{
            return 1;
        }
    }


    //returns an arraylist with even indexes multiplied by 2. Ex: [8]1[0]3[2]0[4]3[1] => [16]1[0]3[4]0[8]3[2]
    public ArrayList calculateIndexes(String ssn){
        ArrayList<Integer> ssnNumbers = new ArrayList<>();
        for(int i = 0; i < (ssn.length()-1); i++){
            if(i%2 == 0){
                ssnNumbers.add(Integer.parseInt(ssn.substring(i,i+1)) * 2);
            }else{
                ssnNumbers.add(Integer.parseInt(ssn.substring(i,i+1)) * 1);
            }
        }

        return ssnNumbers;
    }
    //adds all numbers in arraylist to a string and returns it. Ex: 1+6+1+0+3+4+0+8+3+2
    public String convertIntegersInArrayListToString(ArrayList<Integer> list){
        String controlNumbers = "";
        for (int number:list) {
            controlNumbers+= number;
        }
        return controlNumbers;
    }


    public boolean checkSsnLength(String ssn){
        if(ssn.length() < 10 || ssn.length() > 10){
            return false;
        }
        return true;
    }

    public int calculateSum(String number){
        int tenNumberSum = 0;

        for(int i = 0; i < number.length(); i++){
            tenNumberSum += Integer.parseInt(number.substring(i,i+1));

        }
        return tenNumberSum;
    }



}//End of class
