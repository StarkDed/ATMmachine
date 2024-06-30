import java.util.Objects;
import java.time.LocalTime;

public class Card {
    private int id;
    private String numberCard;
    private String dateCard;
    private short CVV;
    private int balance;
    private short pinCode;
    private byte attempts;
    private long time;

    public Card(String[] arr){
        this.id=Integer.parseInt(arr[0]);
        this.numberCard=arr[1];
        this.dateCard=arr[2];
        this.CVV=Short.parseShort(arr[3]);
        this.balance=Integer.parseInt(arr[4]);
        this.pinCode=Short.parseShort(arr[5]);
        this.attempts=Byte.parseByte(arr[6]);
        this.time=Long.parseLong(arr[7]);
    }

    public void checkBalance(){
        System.out.println("Денег на счету: " +balance);
    }

    public String getNumberCard(){
        return numberCard;
    }

    public boolean takeMoney(int amountOfTake,int balanceInBancomat){
        if(amountOfTake<balanceInBancomat ) {
            if(amountOfTake<balance) {
                balance = balance - amountOfTake;
                System.out.println("Снято со счета " + amountOfTake);
                return true;
            }else
                System.out.println("На карте недостаточно средств");
        }else
            System.out.println("В банкомате недостаточно средств");

        return false;
    }

    public void putInMoney(int amountOfPutIn){
        if(amountOfPutIn<1000000){
            balance+=amountOfPutIn;
            System.out.println("Карта пополнена на "+amountOfPutIn);
        }else
            System.out.println("Сумма пополнения превышает 1000000");
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id && CVV == card.CVV && balance == card.balance && pinCode == card.pinCode && Objects.equals(numberCard, card.numberCard) && Objects.equals(dateCard, card.dateCard);
    }

    public int hashCode() {
        return Objects.hash(id, numberCard, dateCard, CVV, balance, pinCode);
    }

    @Override
    public String toString() {
        return id+"\t"+
                numberCard+"\t"+
                dateCard+"\t"+
                CVV+"\t"+
                balance+"\t"+
                pinCode+"\t"+
                attempts+"\t"+
                time+"\t";
    }

    public boolean inputPinCode(String pinCodeTemp){
        if(time!=0){
            long differenceIfTime=getDifferenceOfTimeInMinutes();
            if(differenceIfTime<1440){
                System.out.print("Вы все еще заблокированы");
                return false;
            }else{
                time=0;
                attempts=3;
            }
        }

        if(!Validity.checkPinCode(pinCodeTemp) || !pinCodeTemp.equals(String.valueOf(pinCode))){
            attempts--;
            System.out.println("Неверный ввод! Осталось "+attempts+" попытки");
            if(attempts==0) {
                time = (LocalTime.now().toSecondOfDay())/60;
                System.out.println("Вы заблокированы на сутки");
            }

            return false;
        }

        attempts=3;

        return true;
    }

    private long getDifferenceOfTimeInMinutes(){
        LocalTime currentTime=LocalTime.now();
        return (currentTime.toSecondOfDay()/60)-time;
    }
}
