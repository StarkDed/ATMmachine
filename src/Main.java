import java.util.*;

public class Main {
    static Scanner sc=new Scanner(System.in);
    static int AmountInBancomat;

    public static void main(String[] args) {
        AmountInBancomat=InformationSave.getAmountOfMoneyATM();
        if(AmountInBancomat!=-1)
            start();
    }

    private static void start(){
        String command;
        while(true) {
            System.out.println();
            System.out.println("""
                    Меню:
                    1 - ввести номер карты;
                    2 - выйти;\s
                    """);
            System.out.print("Введите номер команды(цифрой):");

            command= sc.nextLine();

            if (command.equals("1")) inputCard();
            else if (command.equals("2")) {
                sc.close();
                break;
            } else System.out.println("Команды не существует\n");
        }
    }

    private static void inputCard(){
        System.out.print("Введите номер карты(пример шаблона(2234-5678-9012-3456)):");
        String cardNumber=sc.nextLine().trim();
        System.out.print("Введите номер даты карты:(например: 12/99):");
        String dateCard=sc.nextLine().trim();
        System.out.print("Введите номер CVV:");
        String CVV=sc.nextLine().trim();
        if(Validity.checkCardValidity(cardNumber,dateCard,CVV)) input(cardNumber,dateCard,CVV);
    }

    private static void input(String numberCard,String dateCard,String CVV){
        Card card=InformationSave.getCardFromFile(numberCard,dateCard,CVV);
        if(card!=null) {
            Enter(card);
        }else
            System.out.println("Ошибка. Не удалось найти карту");
    }

    public static void Enter(Card card){
        String command,pincode;
        while (true) {
            System.out.println();
            System.out.println("""
                    Меню:
                    1 - проверить баланс карты;
                    2 - снять средства со счета;
                    3 - пополнить баланс;
                    4 - выйти;\s
                    """);
            System.out.print("Введите номер команды(цифрой):");

            command = sc.nextLine();

            if (command.equals("4")) {
                InformationSave.SaveCard(card);
                break;
            }

            System.out.print("Введите пин-код:");
            pincode = sc.nextLine();

            if (card.inputPinCode(pincode)) {
                if (command.equals("1"))
                    card.checkBalance();
                else if (command.equals("2")) {
                    takeCommand(card);
                } else if (command.equals("3")) {
                    putInCommand(card);
                } else
                    System.out.println("Некорректный ввод");
            }
        }

    }

    private static void takeCommand(Card card){
        String value;
        int valueTemp;
        System.out.print("Введите сумму выдачи наличных:");
        value = sc.nextLine();
        if ((valueTemp= Validity.checkValue(value))!=-1)
            if(card.takeMoney(valueTemp,AmountInBancomat))
                AmountInBancomat-=valueTemp;
    }

    private static void putInCommand(Card card){
        String value;
        int valueTemp;
        System.out.print("Введите сумму пополнения:");
        value = sc.nextLine();
        if ((valueTemp= Validity.checkValue(value))!=-1)
            card.putInMoney(valueTemp);
    }
}