import java.io.*;
import java.util.Random; 
public class BlackJack
{
    String Deck[]= {"Ace of Spades","2 of Spades","3 of Spades","4 of Spades","5 of Spades","6 of Spades","7 of Spades","8 of Spades","9 of Spades","10 of Spades","Jack of Spades","Queen of Spades","King of Spades","Ace of Clubs","2 of Clubs","3 of Clubs","4 of Clubs","5 of Clubs","6 of Clubs","7 of Clubs","8 of Clubs","9 of Clubs","10 of Clubs","Jack of Clubs","Queen of Clubs","King of Clubs","Ace of Diamonds","2 of Diamonds","3 of Diamonds","4 of Diamonds","5 of Diamonds","6 of Diamonds","7 of Diamonds","8 of Diamonds","9 of Diamonds","10 of Diamonds","Jack of Diamonds","Queen of Diamonds","King of Diamonds","Ace of Hearts","2 of Hearts","3 of Hearts","4 of Hearts","5 of Hearts","6 of Hearts","7 of Hearts","8 of Hearts","9 of Hearts","10 of Hearts","Jack of Hearts","Queen of Hearts","King of Hearts"};
    int cardValues[]= {11,2,3,4,5,6,7,8,9,10,10,10,10,11,2,3,4,5,6,7,8,9,10,10,10,10,11,2,3,4,5,6,7,8,9,10,10,10,10,11,2,3,4,5,6,7,8,9,10,10,10,10};
    int numberOfCardsInDeck=52; //to keep track of number of cards
    String Memory[]={"Ace of Spades","2 of Spades","3 of Spades","4 of Spades","5 of Spades","6 of Spades","7 of Spades","8 of Spades","9 of Spades","10 of Spades","Jack of Spades","Queen of Spades","King of Spades","Ace of Clubs","2 of Clubs","3 of Clubs","4 of Clubs","5 of Clubs","6 of Clubs","7 of Clubs","8 of Clubs","9 of Clubs","10 of Clubs","Jack of Clubs","Queen of Clubs","King of Clubs","Ace of Diamonds","2 of Diamonds","3 of Diamonds","4 of Diamonds","5 of Diamonds","6 of Diamonds","7 of Diamonds","8 of Diamonds","9 of Diamonds","10 of Diamonds","Jack of Diamonds","Queen of Diamonds","King of Diamonds","Ace of Hearts","2 of Hearts","3 of Hearts","4 of Hearts","5 of Hearts","6 of Hearts","7 of Hearts","8 of Hearts","9 of Hearts","10 of Hearts","Jack of Hearts","Queen of Hearts","King of Hearts"};
    String pHand[]={"Empty","Empty","Empty","Empty","Empty"}; //Player hand
    String dHand[]={"Empty","Empty","Empty","Empty","Empty"}; //Dealer hand
    int pHandValue=0; //Sum of the player's hand
    int dHandValue=0; //Sum of the player's hand
    int budget=500; //To store the player's balance; the dealer has unlimited money
    int bet=0; // To store the player's bet
    void welcome() throws InterruptedException
    {
        // Welcome screen
        System.out.println("   888888b.   888             d8888  .d8888b.  888    d8P 888888        d8888  .d8888b.  888    d8P     ");
        System.out.println("   888  \"88b  888            d88888 d88P  Y88b 888   d8P    \"88b       d88888 d88P  Y88b 888   d8P;     ");                      //////////////
        System.out.println("   888  .88P  888           d88P888 888    888 888  d8P      888      d88P888 888    888 888  d8P       ");                        //sweet logo//
        System.out.println("   8888888K.  888          d88P 888 888        888d88K       888     d88P 888 888        888d88K        ");                        //////////////
        System.out.println("   888  \"Y88b 888         d88P  888 888        8888888b      888    d88P  888 888        8888888b       ");
        System.out.println("   888    888 888        d88P   888 888    888 888  Y88b     888   d88P   888 888    888 888  Y88b      ");
        System.out.println("   888   d88P 888       d8888888888 Y88b  d88P 888   Y88b    88P  d8888888888 Y88b  d88P 888   Y88b     ");
        System.out.println("   8888888P\"  88888888 d88P     888  \"Y8888P\"  888    Y88b   888 d88P     888  \"Y8888P\"  888    Y88b    ");
        System.out.println("                                                           .d88P                                        ");
        System.out.println("                                                        .d88P                                           ");
        System.out.println();
        System.out.println("Welcome to BlackJack!");
        System.out.println();
        System.out.println();
        System.out.println("Your Starting Amount is $500");
        System.out.println();
        Thread.sleep(1000);

    }

    String dealCard() throws IOException
    {
        Random rand = new Random();
        if(numberOfCardsInDeck==0)
        {
            Deck=Memory;
        }
        String card="Empty";
        int random=0;
        while(card=="Empty")
        {
            random= rand.nextInt(51);
            card= Deck[random];
        }
        Deck[random]="Empty";
        numberOfCardsInDeck--;
        return card;
    }

    void round() throws IOException, InterruptedException

    {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        clearHands();
        System.out.println();
        System.out.println("Your balance:");
        System.out.println("$"+budget);
        if(budget<50)
        {
            System.out.println("Looks like you have run out of funds.");
            System.out.println("Here is $500 to keep you going");
            System.out.println("Enjoy");
            budget+=500;
            System.out.println();
            System.out.println("Your new balance:");
            System.out.println("$"+budget);
        }

        System.out.println();
        System.out.println("Enter bet");
        while(true)
        {
            try{
                bet=Integer.parseInt(br.readLine());
                while(budget-bet<0||bet<50)
                {
                    System.out.println("Please enter a valid bet");
                    System.out.println("The minimum bet is $50");
                    bet=Integer.parseInt(br.readLine());
                }
                break;
            }
            catch(Exception e)
            {
                System.out.println("Please enter a valid bet");
            }
        }

        while(budget-bet<0||bet<50)
        {
            System.out.println("Please enter a valid bet");
            System.out.println("The minimum bet is $50");
            bet=Integer.parseInt(br.readLine());
        }
        budget-=bet;
        String cardret="";
        for(int x=0; x<2;x++)
        {
            cardret=dealCard();
            dHand[x]=cardret;
            cardret=dealCard();
            pHand[x]=cardret;
        }

        printDealerHand();
        printPlayerHand();
        if(dHandValue==21)
        {
            System.out.println();
            System.out.println("Dealer has a BlackJack.");
            System.out.println("Dealer wins round. Better luck next time");
            return;     
        } 

        if(pHandValue==21)
        {
            System.out.println();
            System.out.println("Player has a BlackJack");
            System.out.println("Congratulations! Player wins round");
            budget= bet+bet+ budget;
            return;     
        } 
        char i; //input
        for(int x=2;x<5;x++)
        {
            System.out.println("Would you like a card?");
            System.out.println("y/n");
            while(true)
            {
                try
                {
                    i=(char)br.read();
                    br.readLine();
                    break;
                }
                catch(Exception a)
                {
                    System.out.println("Please enter only 'y' or 'n'");
                    continue;
                }
            }
            if(i=='n')
            {
                break;
            }
            else if(i=='y')
            {
                pHand[x]=dealCard();
                printPlayerHand();
                if(pHandValue>21)
                {
                    System.out.println("Player bust");
                    return;
                }//Ensures hand value is not more than 21
            }
            else
            {
                System.out.println("Please enter only 'y' or 'n'");
                x--; // this is to ensure current number of executions of the loop
                continue;
            }
        }

        for(int x=2;x<5;x++)
        {
            if(pHandValue>dHandValue)
            {
                System.out.println("Dealer chooses to hit");
                dHand[x]=dealCard();
                printDealerHand();
                if(dHandValue>21)
                {
                    System.out.println("Dealer bust");
                    budget= bet+bet+ budget;
                    return;     
                } //Ensures hand value is not more than 21
            }
            else
            {
                System.out.println("Dealer chooses to stand");
                break;
            }
        }

        if(pHandValue>dHandValue)
        {
            System.out.println("Congratulations! Player wins round");
            budget= bet+bet+ budget;
        }
        else if(pHandValue<dHandValue)
        {
            System.out.println(dHandValue);
            System.out.println(pHandValue);
            System.out.println("Dealer wins round. Better luck next time");
        }
        else if(pHandValue==dHandValue)
        {
            System.out.println("Draw");
            budget+=bet;
        }
    }

    void clearHands()
    {
        for(int x=0; x<5;x++)
        {
            pHand[x]="Empty";
            dHand[x]="Empty";
        }
    }

    void printPlayerHand() throws InterruptedException, IOException

    {
        int x=0,position=0;
        pHandValue=0; //As the value is calculated each time, it needs to be reset;
        System.out.println();
        Thread.sleep(1250);
        System.out.println("Your hand:");
        System.out.println("");
        while(pHand[x]!="Empty")
        {
            System.out.println(pHand[x]);
            pHandValue+= cardVal(pHand[x]);
            x++;
            if(x==4)
            {
                break;
            }
        }
        System.out.println("");
        System.out.println("Player Hand Value:"+pHandValue);
        Thread.sleep(1250);
    }

    void printDealerHand() throws InterruptedException, IOException

    {
        dHandValue=0;
        int x=0;
        System.out.println();
        Thread.sleep(1250);
        System.out.println("Dealer's hand:");
        System.out.println("");
        while(dHand[x]!="Empty")
        {
            System.out.println(dHand[x]);
            dHandValue+= cardVal(dHand[x]);
            x++;
            if(x==4)
            {
                break;
            }
        }
        System.out.println("");
        System.out.println("Dealer Hand Value:"+dHandValue);
        Thread.sleep(1250);
    }

    int cardVal(String s) throws IOException
    {
        int cardValue=0;
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        for(int x=0; x<52; x++)
        {
            if(Memory[x]==s)
            {
                if(Memory[x].startsWith("Ace")) //In blackjack, the ace can take the value of 1 or 11. This segment allows the user to choose which value they want 
                {
                    if(pHandValue>0) // This is if the dealer has an ace
                    {
                        if(dHandValue>10)
                        {
                            cardValue=1;
                            return cardValue;
                        }
                        else
                        {
                            cardValue=11;
                            return cardValue;
                        }
                    }
                    int i=0;
                    System.out.println("You have an ace");
                    System.out.println("If you want the ace to count as 1, enter 1");
                    System.out.println("If you want the ace to count as 11, enter 11");
                    while(true)
                    {
                        try
                        {
                            i=Integer.parseInt(br.readLine());
                            if(i==1)
                            {
                                cardValue=1;
                                break;
                            }
                            if(i==11)
                            {
                                cardValue=11;
                                break;
                            }
                            else 
                            {
                                System.out.println("Invalid input. Please try again");
                                continue;
                            }
                        }
                        catch(Exception e)
                        {
                            System.out.println("Invalid input. Please try again");
                            continue;
                        }
                    }

                }
                else
                {
                    cardValue=cardValues[x]; //For all other cards, it refers to the memory deck for the cards value
                }
            }
        }
        return cardValue;
    }

    void input() throws IOException
    {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        boolean validInput=false;
        char c;
        while (validInput==false){
            System.out.println("Input 's' to start");
            System.out.println("Input 'e' to exit");
            while(true)
            {
                try
                {
                    c=(char) br.read();
                    br.readLine();
                    break;
                }
                catch(Exception x)
                {
                    System.out.println("Please enter only 's' or 'e'");
                    continue;
                }
            }
            if(c=='e')
            {
                System.out.println("Thank you for playing");
                System.exit(0);
            }
            else if(c=='s')
            {
                validInput=true;
            }
            else 
            {
                System.out.println("Invalid input");
                System.out.println("Please try again");
                System.out.println();
                System.out.println();
            }
        }
    }
    
    void exit()
    {
        System.out.println("");
        System.out.println("8888888 8888888888 8 8888        8          .8.          b.             8 8 8888     ,88'           `8.`8888.      ,8'  ,o888888o.     8 8888      88                 ");
        System.out.println("      8 8888       8 8888        8         .888.         888o.          8 8 8888    ,88'             `8.`8888.    ,8'. 8888     `88.   8 8888      88                 ");
        System.out.println("      8 8888       8 8888        8        :88888.        Y88888o.       8 8 8888   ,88'               `8.`8888.  ,8',8 8888       `8b  8 8888      88                 ");
        System.out.println("      8 8888       8 8888        8       . `88888.       .`Y888888o.    8 8 8888  ,88'                 `8.`8888.,8' 88 8888        `8b 8 8888      88                 ");
        System.out.println("      8 8888       8 8888        8      .8. `88888.      8o. `Y888888o. 8 8 8888 ,88'                   `8.`88888'  88 8888         88 8 8888      88                 ");
        System.out.println("      8 8888       8 8888        8     .8`8. `88888.     8`Y8o. `Y88888o8 8 8888 88'                     `8. 8888   88 8888         88 8 8888      88                 ");
        System.out.println("      8 8888       8 8888888888888    .8' `8. `88888.    8   `Y8o. `Y8888 8 888888<                       `8 8888   88 8888        ,8P 8 8888      88                 ");
        System.out.println("      8 8888       8 8888        8   .8'   `8. `88888.   8      `Y8o. `Y8 8 8888 `Y8.                      8 8888   `8 8888       ,8P  ` 8888     ,8P                 ");
        System.out.println("      8 8888       8 8888        8  .888888888. `88888.  8         `Y8o.` 8 8888   `Y8.                    8 8888    ` 8888     ,88'     8888   ,d8P                ");
        System.out.println("      8 8888       8 8888        8 .8'       `8. `88888. 8            `Yo 8 8888     `Y8.                  8 8888       `8888888P'        `Y88888P'            ");
        System.out.println("");
        System.out.println("8 8888888888       ,o888888o.     8 888888888o.             8 888888888o   8 8888                  .8.   `8.`8888.      ,8'  8 8888 b.             8     ,o888888o.   ");
        System.out.println("8 8888          . 8888     `88.   8 8888    `88.            8 8888    `88. 8 8888                 .888.   `8.`8888.    ,8'   8 8888 888o.          8    8888     `88. ");
        System.out.println("8 8888         ,8 8888       `8b  8 8888     `88            8 8888     `88 8 8888                :88888.   `8.`8888.  ,8'    8 8888 Y88888o.       8 ,8 8888       `8.");
        System.out.println("8 8888         88 8888        `8b 8 8888     ,88            8 8888     ,88 8 8888               . `88888.   `8.`8888.,8'     8 8888 .`Y888888o.    8 88 8888          ");
        System.out.println("8 888888888888 88 8888         88 8 8888.   ,88'            8 8888.   ,88' 8 8888              .8. `88888.   `8.`88888'      8 8888 8o. `Y888888o. 8 88 8888          ");
        System.out.println("8 8888         88 8888         88 8 888888888P'             8 888888888P'  8 8888             .8`8. `88888.   `8. 8888       8 8888 8`Y8o. `Y88888o8 88 8888          ");
        System.out.println("8 8888         88 8888        ,8P 8 8888`8b                 8 8888         8 8888            .8' `8. `88888.   `8 8888       8 8888 8   `Y8o. `Y8888 88 8888   8888888");
        System.out.println("8 8888         `8 8888       ,8P  8 8888 `8b.               8 8888         8 8888           .8'   `8. `88888.   8 8888       8 8888 8      `Y8o. `Y8 `8 8888       .8'");
        System.out.println("8 8888          ` 8888     ,88'   8 8888   `8b.             8 8888         8 8888          .888888888. `88888.  8 8888       8 8888 8         `Y8o.`    8888     ,88' ");
        System.out.println("8 8888             `8888888P'     8 8888     `88.           8 8888         8 888888888888 .8'       `8. `88888. 8 8888       8 8888 8            `Yo     `8888888P'   ");
        System.out.println("");

        System.exit(0);
    }

    public static void main(String args[]) throws IOException, InterruptedException
    {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BlackJack obj=new BlackJack();
        obj.welcome();
        obj.input();
        char i;
        do{
            obj.round();
            System.out.println();
            System.out.println("Would you like to continue?");
            System.out.println("y/n");
            while(true){
                try
                {
                    i=(char) br.read();
                    br.readLine();
                    break;
                }
                catch(Exception x)
                {
                    System.out.println("Please enter only 'y' or 'n'");
                    continue;
                }
            }
            if(i=='n')
            {
                break;
            }
            else if(i=='y')
            {
                continue;
            }
        }while(true);
        obj.exit();
    }
}



