import java.io.*;

public class InformationSave {
    private static String[] arr;
    private final static String filePath="src/data/Cards";
    private final static int sizeArr=8;
    private final static String filePathATMData="src/data/ATM machine/AmountOfMoney.txt";

    public static int getAmountOfMoneyATM(){
        try(BufferedReader reader=new BufferedReader(new FileReader(filePathATMData))){
            String amount=reader.readLine();
            return Validity.checkAmountATM(amount);
        }catch(IOException ex){
            System.out.println("Файл банкомата поврежден");
            return -1;
        }

    }

    public static Card getCardFromFile(String numberCard,String dateCard,String CVV){
        arr=getArrFromFile(numberCard);

        if(arr!=null && arr.length==sizeArr && checkData(numberCard,dateCard,CVV)) {
            System.out.println("Аутентификация прошла успешна");
            return new Card(arr);
        }
        return null;
    }

    public static boolean checkData(String numberCard,String dateCard,String CVV){
        return Validity.checkId(arr[0]) && arr[1].equals(numberCard) && arr[2].equals(dateCard) && arr[3].equals(CVV) && Validity.checkBalance(arr[4]) && Validity.checkPinCode(arr[5]) && Validity.checkAttempts(arr[6]) && Validity.checkTime(arr[7]);
    }

    public static String[] getArrFromFile(String numberCard){
        String numberCard2=numberCard.replace("-","");

        String pathToCard=filePath+"\\"+numberCard2+".txt";

        try(BufferedReader br=new BufferedReader(new FileReader(pathToCard))){
            String line=br.readLine();

            if(line!=null)
                return line.split("\\s+");
            else
                throw new IOException();

        }catch(IOException ex){
            return null;
        }
    }

    public static void SaveCard(Card card){
        String numberCard2=card.getNumberCard().replace("-","");
        String pathToCard=filePath+"\\"+numberCard2+".txt";
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(pathToCard)))
        {
            writer.write(card.toString());
        }catch(IOException ex){
        System.out.println("Ошибка. Не удалось сохранить транзакцию");

        }
    }

}
