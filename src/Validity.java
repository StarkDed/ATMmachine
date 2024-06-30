import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class  Validity {
    public static boolean checkCardNumber(String cardNumber){
        /*Pattern cardNumberPattern=Pattern.compile("([2-6]([0-9]{3}))(([0-9]{4}){3})");*/
        Pattern cardNumberPattern=Pattern.compile("([2-6]([0-9]{3}))((-([0-9]{4})){3})");
        Matcher cardNumberMatcher=cardNumberPattern.matcher(cardNumber);
        return cardNumberMatcher.matches();
    }

    public static boolean checkDateCard(String dateCard){
        Pattern dateCardPattern=Pattern.compile("(0[1-9]|1[0-2])/([0-9]{2})");
        Matcher dateCardMatcher=dateCardPattern.matcher(dateCard);
        return dateCardMatcher.matches();
    }

    public static boolean checkCVV(String CVV){
        Pattern CVVPattern=Pattern.compile("[0-9]{3}");
        Matcher CVVMatcher=CVVPattern.matcher(CVV);
        return CVVMatcher.matches();
    }

    public static boolean checkCardValidity(String cardNumber,String dateCard,String CVV){
        if(checkCardNumber(cardNumber)){
            if(checkDateCard(dateCard)){
                if(checkCVV(CVV)){
                    return true;
                }else System.out.println("Некорректный номер CVV");
            }else System.out.println("Некорректная дата карты");
        }else System.out.println("Некорректный номер карты.");

        return false;
    }

    public static boolean checkBalance(String balance){
        try{
            int balanceTemp=Integer.parseInt(balance);
            return balanceTemp > -1;

        }catch(NumberFormatException ex){
            return false;
        }
    }

    public static boolean checkPinCode(String pinCode){
        Pattern pinCodePattern=Pattern.compile("[1-9]{4}");
        Matcher pinCodeMatcher=pinCodePattern.matcher(pinCode);
        return pinCodeMatcher.matches();
    }

    public static boolean checkId(String id){
        try{
            int idTemp=Integer.parseInt(id);
            return idTemp>-1;

        }catch(NumberFormatException ex){
            return false;
        }
    }

    public static int checkValue(String value){
        try{
            int valueTemp=Integer.parseInt(value);
            return Math.max(valueTemp, -1);

        }catch(NumberFormatException ex){
            System.out.println("неверный ввод данных");
            return -1;
        }
    }

    public static int checkAmountATM(String value){
        try{
            int valueTemp=Integer.parseInt(value);
            return Math.max(valueTemp, -1);

        }catch(NumberFormatException ex){
            return -1;
        }
    }

    public static boolean checkAttempts(String attempts){
        try{
            byte attemptsTemp=Byte.parseByte(attempts);
            return attemptsTemp>-1;

        }catch(NumberFormatException ex){
            return false;
        }
    }

    public static boolean checkTime(String time){
        try{
            long timeTemp=Long.parseLong(time);
            return timeTemp>-1;

        }catch(NumberFormatException ex){
            return false;
        }
    }
}


