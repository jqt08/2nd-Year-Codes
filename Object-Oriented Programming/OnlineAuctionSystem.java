import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OnlineAuctionSystem {
    private static int nextItemNumber = 1;
    private static final int MAX_BUYERS = 100;
    private static final int MAX_SELLERS = 100;
    private static final int MAX_ITEMS = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Buyer[] buyers = new Buyer[MAX_BUYERS];
        Seller[] sellers = new Seller[MAX_SELLERS];
        Item[] items = new Item[MAX_ITEMS];
        int buyerCount = 0;
        int sellerCount = 0;
        int itemCount = 0;

        while (true) {
            System.out.println("Online Auction System");
            System.out.println("1. Buyer");
            System.out.println("2. Seller");
            System.out.println("3. Auction Winners");
            System.out.println("4. Feedback");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    buyerMenu(scanner, buyers, sellerCount, sellers, items, itemCount);
                    break;
                case 2:
                    sellerMenu(scanner, sellers, sellerCount, items, itemCount);
                    break;
                case 3:
                    displayAuctionWinners(items);
                    break;
                case 4:
                    leaveFeedback(scanner, buyers, sellers, items, itemCount);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void buyerMenu(
        Scanner scanner, Buyer[] buyers, int sellerCount, Seller[] sellers, Item[] items, int itemCount) {
        int buyerCount = buyers.length;

        while (true) {
            System.out.println("Buyer Menu");
            System.out.println("1. Create a new buyer account");
            System.out.println("2. Log in as a buyer");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int buyerOption = scanner.nextInt();
            scanner.nextLine();

            if (buyerOption == 1) {
                if (buyerCount < MAX_BUYERS) {
                    buyers[buyerCount] = createBuyer(scanner);
                    buyerCount++;
                } else {
                    System.out.println("Maximum number of buyers reached.");
                }
            } else if (buyerOption == 2) {
                Buyer loggedInBuyer = loginBuyer(scanner, buyers, buyerCount);
                if (loggedInBuyer != null) {
                    buyerMenuOptions(scanner, loggedInBuyer, sellers, items, itemCount);
                } else {
                    System.out.println("Login failed.");
                }
            } else if (buyerOption == 3) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private static void sellerMenu(
        Scanner scanner, Seller[] sellers, int sellerCount, Item[] items, int itemCount) {
        int sellerOption;
        int itemCountUpdated = itemCount;

        while (true) {
            System.out.println("Seller Menu");
            System.out.println("1. Create a new seller account");
            System.out.println("2. Log in as a seller");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            sellerOption = scanner.nextInt();
            scanner.nextLine();

            if (sellerOption == 1) {
                if (sellerCount < MAX_SELLERS) {
                    sellers[sellerCount] = createSeller(scanner);
                    sellerCount++;
                } else {
                    System.out.println("Maximum number of sellers reached.");
                }
            } else if (sellerOption == 2) {
                Seller loggedInSeller = loginSeller(scanner, sellers, sellerCount);
                if (loggedInSeller != null) {
                    itemCountUpdated = sellerMenuOptions(scanner, loggedInSeller, items, itemCountUpdated);
                } else {
                    System.out.println("Login failed.");
                }
            } else if (sellerOption == 3) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private static Buyer createBuyer(Scanner scanner) {
        System.out.println("Creating a new buyer account:");
        return new Buyer(scanner);
    }

    private static Seller createSeller(Scanner scanner) {
        System.out.println("Creating a new seller account:");
        return new Seller(scanner);
    }

    private static Buyer loginBuyer(Scanner scanner, Buyer[] buyers, int buyerCount) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (int i = 0; i < buyerCount; i++) {
            if (buyers[i] != null && buyers[i].getEmail().equals(email) && buyers[i].getPassword().equals(password)) {
                return buyers[i];
            }
        }
        return null;
    }

    private static Seller loginSeller(Scanner scanner, Seller[] sellers, int sellerCount) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (int i = 0; i < sellerCount; i++) {
            if (sellers[i] != null && sellers[i].getEmail().equals(email) && sellers[i].getPassword().equals(password)) {
                return sellers[i];
            }
        }
        return null;
    }

    private static void buyerMenuOptions(
        Scanner scanner, Buyer buyer, Seller[] sellers, Item[] items, int itemCount) {
        while (true) {
            System.out.println("\nBuyer Menu");
            System.out.println("1. View Items");
            System.out.println("2. Bid on Item");
            System.out.println("3. View My Bids");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int buyerChoice = scanner.nextInt();
            scanner.nextLine();

            switch (buyerChoice) {
                case 1:
                    System.out.println("Items listed by sellers:");
                    for (Seller seller : sellers) {
                        if (seller != null) {
                            Item[] sellerItems = seller.getItemsListed(items);
                            for (Item item : sellerItems) {
                                System.out.println(item.getItemNumber() + ". " + item.getItemName() + " (Seller: " + seller.getName() + ")");
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter the item number you want to bid on: ");
                    int itemNumberToBid = scanner.nextInt();
                    scanner.nextLine();

                    if (itemNumberToBid >= 1 && itemNumberToBid <= itemCount) {
                        Item selectedItem = items[itemNumberToBid - 1];
                        if (selectedItem != null) {
                            Seller seller = selectedItem.getSeller();
                            System.out.println("Enter your bid amount for '" + selectedItem.getItemName() + "' listed by " + seller.getName() + ": ");
                            double bidAmount = scanner.nextDouble();
                            scanner.nextLine();

                            if (buyer.placeBid(selectedItem, bidAmount)) {
                                System.out.println("Bid placed successfully!");
                            } else {
                                System.out.println("Bid amount is not higher than the current bid or the item has reached the maximum bid count.");
                            }
                        } else {
                            System.out.println("Item not found.");
                        }
                    } else {
                        System.out.println("Invalid item number.");
                    }
                    break;
                case 3:
                    System.out.println("My Bids:");
                    buyer.viewMyBids();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static int sellerMenuOptions(
        Scanner scanner, Seller seller, Item[] items, int itemCount) {
        while (true) {
            System.out.println("\nSeller Menu");
            System.out.println("1. Create a New Item Listing");
            System.out.println("2. View My Listed Items");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int sellerChoice = scanner.nextInt();
            scanner.nextLine();

            switch (sellerChoice) {
                case 1:
                    itemCount = seller.createItem(scanner, items, itemCount);
                    System.out.println("Item listed successfully!");
                    break;

                case 2:
                    System.out.println("My Listed Items:");
                    for (Item item : items) {
                        if (item != null && item.getSeller() == seller) {
                            System.out.println(item.getItemNumber() + ". " + item.getItemName());
                        }
                    }
                    break;
                case 3:
                    return itemCount;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayAuctionWinners(Item[] items) {
        System.out.println("Winners of Auctions:");
        for (Item item : items) {
            if (item != null && item.isSold()) {
                System.out.println("Item: " + item.getItemName() + " (Winner: " + item.getHighestBidder().getName() + ")");
            }
        }
    }

    private static void leaveFeedback(
        Scanner scanner, Buyer[] buyers, Seller[] sellers, Item[] items, int itemCount) {
        System.out.println("Select an item to rate (Enter the item number):");
        for (int i = 0; i < itemCount; i++) {
            if (items[i] != null) {
                System.out.println((i + 1) + ". " + items[i].getItemName() + " (Seller: " + items[i].getSeller().getName() + ")");
            }
        }

        int itemNumber = scanner.nextInt();
        scanner.nextLine();

        if (itemNumber >= 1 && itemNumber <= itemCount) {
            Item itemToRate = items[itemNumber - 1];
            if (itemToRate != null && itemToRate.getSeller() != null) {
                System.out.println("Rate the item from 1 to 10:");
                int rating = scanner.nextInt();
                scanner.nextLine();

                if (rating >= 1 && rating <= 10) {
                    System.out.println("Thank you for your feedback!");
                } else {
                    System.out.println("Invalid rating. Please rate the item from 1 to 10.");
                }
            } else {
                System.out.println("Item not found.");
            }
        } else {
            System.out.println("Invalid item number.");
        }
    }

    static class Buyer {
        private String email;
        private String name;
        private String password;
        private String homeAddress;
        private String phoneNumber;
        private double accountBalance;
        private List<Item> myBids;

        public Buyer(Scanner scanner) {
            System.out.println("Creating a new buyer account:");
            System.out.print("Enter email: ");
            this.email = scanner.nextLine();
            System.out.print("Enter name: ");
            this.name = scanner.nextLine();
            System.out.print("Enter password: ");
            this.password = scanner.nextLine();
            System.out.print("Enter home address: ");
            this.homeAddress = scanner.nextLine();
            System.out.print("Enter phone number: ");
            this.phoneNumber = scanner.nextLine();
            this.accountBalance = 0.0;
            this.myBids = new ArrayList<>();
        }

        public boolean placeBid(Item item, double bidAmount) {
            if (bidAmount > item.getCurrentBidPrice() && !item.isSold()) {
                if (item.getBidCount() < 3) {
                    item.setCurrentBidPrice(bidAmount);
                    item.setHighestBidder(this);
                    item.incrementBidCount();
                    myBids.add(item);
                    if (item.getBidCount() >= 3) {
                        item.setSold(true);
                    }
                    return true;
                } else {
                    System.out.println("Item has reached the maximum bid count.");
                }
            } else {
                System.out.println("Bid amount is not higher than the current bid or the item has been sold.");
            }
            return false;
        }

        public String getName() {
            return name;
        }

        public void viewMyBids() {
            for (Item item : myBids) {
                System.out.println("Item: " + item.getItemName() + " (Current Bid: " + item.getCurrentBidPrice() + ")");
            }
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }

    static class Seller {
        private String email;
        private String name;
        private String password;
        private String homeAddress;
        private String bankAccountNumber;
        private String routingNumber;
        private String phoneNumber;

        public Seller(Scanner scanner) {
            System.out.println("Creating a new seller account:");
            System.out.print("Enter email: ");
            this.email = scanner.nextLine();
            System.out.print("Enter name: ");
            this.name = scanner.nextLine();
            System.out.print("Enter password: ");
            this.password = scanner.nextLine();
            System.out.print("Enter home address: ");
            this.homeAddress = scanner.nextLine();
            System.out.print("Enter bank account number: ");
            this.bankAccountNumber = scanner.nextLine();
            System.out.print("Enter routing number: ");
            this.routingNumber = scanner.nextLine();
            System.out.print("Enter phone number: ");
            this.phoneNumber = scanner.nextLine();
        }

        public Item[] getItemsListed(Item[] allItems) {
            List<Item> listedItems = new ArrayList<>();
            for (Item item : allItems) {
                if (item != null && item.getSeller() == this) {
                    listedItems.add(item);
                }
            }
            return listedItems.toArray(new Item[0]);
        }

        public int createItem(Scanner scanner, Item[] items, int itemCount) {
            if (itemCount < MAX_ITEMS) {
                items[itemCount] = new Item(scanner, this);
                itemCount++;
                return itemCount;
            } else {
                System.out.println("Maximum number of items reached.");
                return itemCount;
            }
        }

        public String getEmail() {
            return email;
        }

        public String getName() {
            return name;
        }

        public String getPassword() {
            return password;
        }
    }

    static class Item {
        private int itemNumber;
        private String itemName;
        private String description;
        private double startingBidPrice;
        private double currentBidPrice;
        private double highestBid;
        private Buyer highestBidder;
        private Seller seller;
        private int bidCount;
        private boolean sold;

        public Item(Scanner scanner, Seller seller) {
            this.itemNumber = nextItemNumber++;
            this.seller = seller;

            System.out.println("Creating a new item listing:");
            System.out.print("Enter item name: ");
            this.itemName = scanner.nextLine();
            System.out.print("Enter item description: ");
            this.description = scanner.nextLine();
            System.out.print("Enter starting bid price: ");
            this.startingBidPrice = scanner.nextDouble();
            this.currentBidPrice = this.startingBidPrice;
            this.highestBid = this.startingBidPrice;
            this.highestBidder = null;
            this.bidCount = 0;
            this.sold = false;
            scanner.nextLine();
        }

        public void setSold(boolean sold) {
            this.sold = sold;
        }

        public int getItemNumber() {
            return itemNumber;
        }

        public String getItemName() {
            return itemName;
        }

        public double getCurrentBidPrice() {
            return currentBidPrice;
        }

        public void setCurrentBidPrice(double currentBidPrice) {
            this.currentBidPrice = currentBidPrice;
        }

        public Buyer getHighestBidder() {
            return highestBidder;
        }

        public void setHighestBidder(Buyer highestBidder) {
            this.highestBidder = highestBidder;
        }

        public Seller getSeller() {
            return seller;
        }

        public int getBidCount() {
            return bidCount;
        }

        public void incrementBidCount() {
            bidCount++;
            if (bidCount >= 3) {
                this.sold = true;
            }
        }

        public boolean isSold() {
            return sold;
        }
    }
}
