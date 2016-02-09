
/** 1.Ask if another customer is waiting in line, and have the user enter an integer - 1 for yes and 2 for no.
   
   2.  If no customers are waiting in line, exit the program.
   
   3.  Show the customer a menu which has all items and their prices listed, as well as options to show the current order and check out. Customers should be able to modify their order (for example, if they accidentally enter that they want to purchase four books, then put one back, they should be able to modify that to three books).
   
   4.  Showing the current order should show how many of each item the customer is going to purchase.
   
   5.  Checking out will consist of showing a tabulated receipt with how many of each item was purchased. If an item was not purchased, it should not display (so there should be no "Books: 0 $0.00" lines. If the "Third Customer Discount" was applied, it should show that as a separate (before tax) line. It should include a subtotal (before tax), and then a total (after tax).

   6.  Finally, ask the user to pay for it. The system should accept a floating-point number from the user. If it is less than the cost of the customer's purchase, the system should print "Not enough money - please re-enter" and ask again. The system should continue to do this until a valid amount of money (the cost of the total or greater) is entered. Once a valid amount of money is entered, indicate the change given (for example, if the purchase is $96.50 and the customer gives $100.00, show that the change should be $3.50).**/
import static java.lang.System.in;
import java.util.Scanner;
public class TransactionMachine {
	
	
	
    public static void main(String[]args){
	/* Declaring variables*/
	Scanner a = new Scanner(System.in);
	int waitingCustomer = 0;
	int numPurchase = 0;
	String booksTotal = "";
	String bmTotal = "";
	String pTotal = "";
	String saveMsg = "";
	int tBookPurchase = 0;
	int tBmPurchase = 0;
	int tpPurchase = 0;
	String purchaseChoice = "";
	float bmSubtotal = (float) 0.00;
	int discountNum = 0;
	boolean discount = false;
	float discountRecpt = 0;

	/*I stored these menus as variables for easy referencing*/
	String askCustomer = ("Is there a customer waiting in line? (1|YES  2|NO): ");
	String priceMenu =( "\n1 - Buy Books - $5.00 each\n \n" +
					"2 - Buy Bookmarks - $1.00 each, $5.00 for a six-pack\n \n" + 
					"3 - Buy Paintings of Books - $100.00 each\n \n" +
					"4 - See current order\n \n" +
					"5 - Checkout \n \n" +
					"Please enter a valid option (1 through 5) > ");
					
	System.out.print(askCustomer);
    waitingCustomer = a.nextInt();
	while (waitingCustomer == 1) { /* An inifite loop means the user can keep shopping indefinitely*/
		System.out.print(priceMenu);
		Scanner sc = new Scanner(System.in);
		int userPurchase = sc.nextInt();	

		if (userPurchase == 1){/* Buying books*/
			System.out.println("Currently in cart: " + tBookPurchase + " books.");
			purchaseChoice = ("Books");
			System.out.print("How many " + purchaseChoice + " do you want to buy?: ");
			Scanner b = new Scanner(System.in);
			int cBookPurchase = b.nextInt();
			if (cBookPurchase < 0){
				System.out.println("If you want to sell books, you must go to the second floor");/*Error handling*/
				cBookPurchase = 0;
			}
			tBookPurchase = tBookPurchase + cBookPurchase;
			booksTotal = (purchaseChoice + ": " + tBookPurchase);

		}else if (userPurchase == 2){/*buying bookmarks*/
			System.out.println("Currently in cart: " + tBmPurchase + " bookmarks.");
			purchaseChoice = ("Bookmarks");
			System.out.print("How many " + purchaseChoice + " do you want to buy?: ");
			Scanner bm = new Scanner(System.in);
			int cBmPurchase = bm.nextInt();
			if (cBmPurchase < 0){
				System.out.println("If you want to sell bookmarks, you must go to the second floor");
				cBmPurchase = 0;
			}
			tBmPurchase = tBmPurchase + cBmPurchase;
			bmTotal = (purchaseChoice + ": " + tBmPurchase);

		}else if (userPurchase == 3){/*Buying paintings*/
			System.out.println("Currently in cart: " + tpPurchase + " paintings of books.");
			purchaseChoice = ("Paintings of Books");
			System.out.print("How many " + purchaseChoice + " do you want to buy?: ");
			Scanner pnt = new Scanner(System.in);
			/* try {Scanner pnt = new Scanner(System.in);
			}catch (InputMismatchException exception) 
			{ 
				System.out.println("Integers only, please."); 
				scanner.nextLine();
			} I tried to catch expceptions in an attempt to curb non integer values here. Didn't work.*/
			int cpPurchase = pnt.nextInt();
			if (cpPurchase < 0){
				System.out.println("If you want to sell paintings, you must go to the second floor");
				cpPurchase = 0;
			}
			tpPurchase = tpPurchase + cpPurchase;
			pTotal = (purchaseChoice + ": " + tpPurchase);

		}else if (userPurchase == 4){
			System.out.println("Currently in cart: \n" + booksTotal + "\n" + bmTotal + "\n" + pTotal);
		/*pricing*/
		}else if (userPurchase == 5){
			float bSubtotal = (float) 005.00 * tBookPurchase;
			if (tBmPurchase >= 6){/*sexy discount math*/
				float NewTBMPurchase = (float) tBmPurchase - tBmPurchase % 6; 
				float discTBMSubtotal = (float) (005.00/006.00) * NewTBMPurchase;
				bmSubtotal = discTBMSubtotal + tBmPurchase % 6;
			}
			else{
				bmSubtotal = (float) 001.00 * tBmPurchase	;
			}
			float pntSubtotal = (float) 100.00 * tpPurchase;
			float finalSubtotal = bSubtotal + bmSubtotal + pntSubtotal;
			discountNum++;
			if (discountNum % 3 == 0){/*this should be through for every third go around*/
				discount = true ;
				finalSubtotal = (float) (finalSubtotal * 0.90);
				discountRecpt = (float) (finalSubtotal * 0.10);
				saveMsg = ("You won a 10% discount!");
			}else{
				discount = false ;
				saveMsg = ("You did not get a discount! Better luck next time!\n" +
							booksTotal + "\n" + bmTotal + "\n" + pTotal + "\n");
			}
		
			/*taxes*/
			float cartTax = (float) 00.07 * finalSubtotal;
			
			float finalPrice = finalSubtotal + cartTax;
			
			System.out.print("--------------------------------------\n" +
								saveMsg + "\n");
			if (discount == true){ /* shows money saved, IF there is a discount.*/
				System.out.println("Discount Saved:						-$" + discountRecpt);
			}				
			System.out.print("\nSubtotal:						$" + finalSubtotal + 
								" \nTax:							$" + 
								cartTax + "\n" +  
								"\n\n\n\n\nFinal Price:						" + "$" + finalPrice + 
								"\n\n\n--------------------------------------\n" );
			System.out.print("Enter amount paid(-XXX- We DO NOT  accept change:");
			Scanner pay = new Scanner(System.in); /* Pay for your items*/
			int userPayment = pay.nextInt();
			if ( userPayment< finalPrice){
				System.out.println("We're calling the cops.");
				userPayment = 0;
			}
			float change = (float) (001.00 * (userPayment - finalPrice));
			System.out.print("Your change is " + ": $" + change + "\n");
			System.out.println("Thanks for shopping? *NOTE* Due to exchange rate instablity, prices may differ slightly at checkout. \n We thank you for your understanding.");
			
			/*reset everything for the next customer*/
			tBmPurchase = 0;
			tBookPurchase = 0;
			tpPurchase = 0;
			booksTotal = "";
			bmTotal = "";
			pTotal = "";
			
			System.out.print(askCustomer);
			waitingCustomer = a.nextInt();
		}else{
			System.out.println("Please enter only a valid input.");
		}
			
         }			
	if (waitingCustomer != 1) ;
		System.out.println("No more customers! Closing time!");
	
}
}
