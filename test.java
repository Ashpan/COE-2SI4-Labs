import java.util.Random;
import java.lang.StringBuilder;

public class HugeInteger {
    private int[] number;         //Number array to store HugeInteger data
    private boolean isNegative = false; //stores if Hugeint is negative or positive

    protected HugeInteger(String val) throws IllegalArgumentException{
        if(!(val.matches("^[0-9]*$") || (val.substring(1).matches("^[0-9]*$") && val.substring(0,1).equals("-")))){
            //if statement checks if "val" is either all numbers from 0 to 9, or starts with a '-' and the rest are digits from 0-9
            throw new IllegalArgumentException("You entered a non integer as the parameter.");
            //if it doesnt match throw error
        }
        int startLoop = 0; //see where to start reading the string in case of leading zeroes or negative sign
        if(val.charAt(0) == '-') { //if it starts with a '-' start at index 1
            isNegative = true;
            startLoop = 1;
        }
        boolean brokeLoop = false;
        for (int i = startLoop; i < val.length(); i++) { //keep cycling in string from startLoop and go until we hit a
//            non zero value (in order to ignore leading zeros)
            if(val.charAt(i) != '0'){
                startLoop = i;
                brokeLoop = true;
                break;
            }
        }
        if(!brokeLoop) {
            number = new int[1];
            number[0] = 0;
            isNegative = false;
        }
        int size = val.length() - startLoop;
        number = new int[size];
        for (int i = startLoop; i < val.length(); i++) {
            number[i-startLoop] = Integer.parseInt(String.valueOf(val.charAt(i))); //store the digits into the num array
        }
    }

    protected HugeInteger(int n) throws IllegalArgumentException{
        Random random = new Random(); //Instantiate random object
        if(n<1) //if input is less than 1, throw exception
            throw new IllegalArgumentException("You entered a parameter of less than 1, please enter a value that is greater than 0");
        number = new int[n]; //create number array of size n
        for (int i = 0; i < number.length; i++) { //iterate through number array to store each digit for n number of times
            int ranVal;
            if(i == 0){
                ranVal = random.nextInt(9)+1; //if first digit, select random number between 1 and 9
            }else{
                ranVal = random.nextInt(10); //otherwise select a digit between 0 and 9
            }
            number[i] = ranVal;
        }
    }

    public int getLength(){
        return this.number.length; //method to get size of huge int
    }

    HugeInteger add(HugeInteger h){
        if(this.isNegative && !h.isNegative)
            return h.subtract(this);
        if(!this.isNegative && h.isNegative)
            return this.subtract(h);
        HugeInteger longer;
        HugeInteger shorter;
        //create a longer and shorter hugeInt to store the long and short one if they are different sizes
        int size;
        if(this.getLength() < h.getLength()){ //if this is shorter than h, set h to the longer, and this to the shorter
            longer = h;
            shorter = this;
        }else{  //otherwise set them up the other way
            longer = this;
            shorter = h;
        }
        String reverseLonger = "";
        String reverseShorter = "";

        //the following 2 for loops create a string in the reverse order of the huge int to make operations easier

        for(int i = longer.toString().length() - 1; i >= 0; i--){
            if(longer.toString().charAt(i) != '-')
                reverseLonger += longer.toString().charAt(i);
        }
        for(int i = shorter.toString().length() - 1; i >= 0; i--){
            if(shorter.toString().charAt(i) != '-')
                reverseShorter += shorter.toString().charAt(i);
        }

        //add any leading zeros if the shorter hugeInt is shorter than the longer one
        for (int i = 0; i < longer.getLength()-shorter.getLength(); i++){
            reverseShorter += '0';
        }
        int carry = 0; //set carry to 0
        String reverseSum = ""; //create reverse sum string
        for (int i = 0; i < reverseLonger.length(); i++) {
            reverseSum += (Character.getNumericValue(reverseLonger.charAt(i)) + Character.getNumericValue(reverseShorter.charAt(i)) + carry)%10;
            // adds the two digits together and takes its modulus of 10, to get the digit in that column
            carry = (Character.getNumericValue(reverseLonger.charAt(i)) + Character.getNumericValue(reverseShorter.charAt(i)) + carry)/10;
            System.out.println(i);
            // adds the two digits and takes the integer division of the sum by 10 to get the carry value
        }
        if(carry != 0) //if at the end the carry digit is not 0
            reverseSum += carry; //add the carry value of the string
        if(this.isNegative && h.isNegative)
            reverseSum += "-";
        String fixedSum = ""; //create un-reverse sum string
        for(int i = reverseSum.length() - 1; i >= 0; i--){ //flip the string
            fixedSum += reverseSum.charAt(i);
        }
        return new HugeInteger(fixedSum); //return a new HugeInt with the "fixedSum" string
    }

    HugeInteger subtract(HugeInteger h) {
        boolean neg = false; //negative
        if(this.isNegative && h.isNegative){ //neg-neg
            h.isNegative = false;
            return this.add(h);
        }else if(this.isNegative){ //neg-pos
            this.isNegative = false;
            HugeInteger ans = this.add(h);
            ans.isNegative = true;
            return ans;
        }else if(h.isNegative) { //pos-neg
            h.isNegative = false;
            return(this.add(h));
        }
        HugeInteger bigger, smaller; //set the smaller and larger
        if(this.compareTo(h) == 1){ //if this>h, bigger = this smaller=h
            bigger = this; smaller = h;
        }else if(this.compareTo(h) == -1){ //if this<=h, bigger = h smaller=this
            smaller = this; bigger = h; neg = true;
        }else{
            return new HugeInteger("0"); //if they're the same number, subtraction is 0
        }

        StringBuilder answer = new StringBuilder(""); //create empty string with the answer
        boolean borrow = false; //borrow variable for sub
        for (int i = bigger.getLength()-1; i >= 0; i--) { //iterate from rightmost element from bigger num
            int diff = bigger.getLength() - smaller.getLength(); //get the different in length of numbers
            if(i >= diff){ //only run if index is not less than difference
                if(borrow) //if borrow, reduce bigger num by 1
                    bigger.number[i] -= 1;
                if(bigger.number[i]-smaller.number[i-diff] < 0){ //if subtraction is negative, set borrow to true
                    borrow = true; //and then add 10 to borrow the number
                    bigger.number[i] += 10;
                }else{
                    borrow = false; //otherwise borrow is false
                }
                answer.insert(0, bigger.number[i]-smaller.number[i-diff]); //insert the subtraction into string
            }else{ //once we are out of range for the smaller number
                if(borrow) //if borrowing do the same as before
                    bigger.number[i] -= 1;
                borrow = false; //and then set borrow to false
                answer.insert(0, bigger.number[i]); //insert the value of the bigger num to the answer
            }
        }
        if(neg) //if num is negative
            answer.insert(0, "-"); //insert a negative sign
        return new HugeInteger(answer.toString()); //return answer
    }

    HugeInteger multiply(HugeInteger h) {
        boolean neg = false;    //negatory
        if(this.isNegative & !h.isNegative) //if signs are opposite, make it negative
            neg = true;
        if(!this.isNegative & h.isNegative)
            neg = true;

        HugeInteger bigger, smaller; //set the smaller and larger
        if(this.compareTo(h) == 1){
            bigger = this; smaller = h;
        }else{
            smaller = this; bigger = h;
        }
        HugeInteger sum = new HugeInteger("0"); //create a sum number, to start as 0
        int counter = 0; //counter for which row you are adding from
        StringBuilder answer = new StringBuilder(""); //answer string
        for (int i = bigger.getLength()-1; i >= 0; i--) { //iterate from bigger number from LSD to MSD
            answer = new StringBuilder(""); //reset answer string after going through each value in smaller num
            int carry = 0;
            for (int j = smaller.getLength()-1; j >= 0; j--) { //iterate from smaller number from LSD to MSD
                answer.insert(0, (bigger.number[i]*smaller.number[j]+carry)%10); //multiply and add carry, mod 10 as answer
                carry = (bigger.number[i]*smaller.number[j]+carry)/10; //carry is the same except int division by 10
            }
            if(carry != 0) //after if there is a carry left, add to the string
                answer.insert(0, carry);
            for (int j = 0; j < counter; j++) //add 0's at LSD counter number of times
                answer.insert(answer.length(), 0);
            HugeInteger ans = new HugeInteger(answer.toString()); //use answer string to create an ans HugeInt
            sum = sum.add(ans); //add that ^ to the sum HugeInt
            counter++; //increment counter
        }
        if(neg) //set isNegative true if neg is true
            sum.isNegative = true;
        return (sum); //return sum
    }


    int compareTo(HugeInteger h){
        if(this.getLength() > h.getLength())
            return 1;
        else if(h.getLength() > this.getLength())
            return -1;
        else{
            for (int i = 0; i < this.getLength(); i++) {
                if(this.number[i] > h.number[i])
                    return 1;
                else if(h.number[i] > this.number[i])
                    return -1;
            }
        }
        return 0;
    }

    public String toString(){
        String val = "";
        if(this.isNegative)
            val += "-";
        for (int i = 0; i < this.getLength(); i++) {
            val += number[i];
        }
        if(this.getLength() == 1 && this.number[0] == 0){
            val = "0";
        }
        return val;
    }
}