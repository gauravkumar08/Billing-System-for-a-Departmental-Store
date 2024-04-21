import java.io.*;
import java.util.Scanner;

class BillingSystem {
    static class Item {
        int productNo;
        String productName;
        int quantity;
        int price;
    }

    static class Customer {
        int productNo;
        String productName;
        int quantity;
        int price;
        int amount;
    }

    static class Temporary {
        int productNo;
        String productName;
        int quantity;
        int price;
        int amount;
    }

    static Item item = new Item();
    static Customer[] cst = new Customer[100];
    static Temporary[] cstr = new Temporary[100];
    static int t = 0;
    static int n = 0;
    static int k = 0;
    static int ba = 0;
    static int ti = 0;

    public static void create() throws IOException {
        int i = 0;
        PrintWriter fp = new PrintWriter(new FileWriter("Records.txt"));
        System.out.println("\tEnter the Number of Records:");
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        PrintWriter fpq = new PrintWriter(new FileWriter("Quantity.txt"));
        fp.println("productno\tProductName\tQuantity\nPrice\t");
        fpq.println(n);
        fpq.close();
        while (i < n) {
            System.out.println("\tEnter Product Code:");
            item.productNo = scanner.nextInt();
            System.out.println("\tEnter Product Name:");
            item.productName = scanner.next();
            System.out.println("\tEnter Quantity:");
            item.quantity = scanner.nextInt();
            System.out.println("\tEnter Price:");
            item.price = scanner.nextInt();
            i++;
            fp.println(item.productNo + "\t" + item.productName + "\t" + item.quantity + "\t" + item.price + "\t");
        }
        fp.close();
        System.out.println("\tRecords are Created");
    }

    public static void bill() {
        Scanner scanner = new Scanner(System.in);
        int h;
        boolean found = false;
        System.out.println("\tEnter the Product Number to Bill: ");
        h = scanner.nextInt();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Records.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                int productNo = Integer.parseInt(parts[0]);
                if (productNo == h) {
                    found = true;
                    System.out.println("\tEnter Quantity:");
                    item.quantity = scanner.nextInt();
                    int total = Integer.parseInt(parts[3]) * item.quantity;
                    System.out.println("\tTotal Amount: " + total);
                    break;
                }
            }
            br.close();
            if (!found) {
                System.out.println("\tProduct not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void invoice() {
        System.out.println("\tGenerating Invoice...");
    }

    public static void mdisplay() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Records.txt"));
            String line;
            System.out.println("\tProduct No\tProduct Name\tQuantity\tPrice");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                System.out.println("\t" + parts[0] + "\t\t" + parts[1] + "\t\t" + parts[2] + "\t\t" + parts[3]);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mdelete() {
        int h;
        boolean found = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\tEnter the Product Number to Delete: ");
        h = scanner.nextInt();

        File inputFile = new File("Records.txt");
        File tempFile = new File("temp.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] parts = currentLine.split("\t");
                int productNo = Integer.parseInt(parts[0]);
                if (productNo == h) {
                    found = true;
                    continue;
                }
                writer.write(currentLine);
                writer.newLine();
            }

            writer.close();
            reader.close();
            inputFile.delete();
            tempFile.renameTo(inputFile);

            if (!found) {
                System.out.println("\tProduct not found");
            } else {
                System.out.println("\tProduct deleted successfully");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int s = 0;
        int y = 0;

        System.out.println("************************************************************************************************************************");
        System.out.println("*****************************************    WELCOME TO DENIMXPRESS    *************************************************");
        System.out.println("\t                               YOU ARE LUCKY TO BE SHOPPING HERE");
        System.out.println("\t\t\tPlease note that all the Product's Price are Discounted and are Genuine");
        System.out.println("\t\t\t\t\tReference Number: " + n);
        while (true) {
            System.out.println("****************************");
            System.out.println("\tPlease Select an Option: ");
            System.out.println("****************************");
            System.out.println("\t01. ADMINISTRATOR");
            System.out.println("\t02. CUSTOMER");
            System.out.println("\t03. EXIT");
            System.out.println("\tPlease Enter an Option: ");
            s = scanner.nextInt();

            switch (s) {
                case 1:
                    System.out.println("\tEnter Password:");
                    String password = scanner.next();
                    if (!password.equals("progto")) {
                        System.out.println("Wrong Password. Please Try Again");
                        break;
                    }
                    System.out.println("\tAccess Granted");
                    while (true) {
                        System.out.println("****************************");
                        System.out.println("\tPlease Select an Option: ");
                        System.out.println("****************************");
                        System.out.println("\t01.CREATE");
                        System.out.println("\t02.DELETE/MODIFY");
                        System.out.println("\t03.DISPLAY");
                        System.out.println("\t04.MAIN MENU");
                        y = scanner.nextInt();
                        switch (y) {
                            case 1:
                                create();
                                break;
                            case 2:
                                mdelete();
                                break;
                            case 3:
                                mdisplay();
                                break;
                            case 4:
                                continue;
                            default:
                                System.out.println("Error");
                                break;
                        }
                    }
                case 2:
                    while (true) {
                        System.out.println("****************************");
                        System.out.println("\tPlease Select an Option: ");
                        System.out.println("****************************");
                        System.out.println("\t01.BILL");
                        System.out.println("\t02.INVOICE");
                        System.out.println("\t03.DISPLAY");
                        System.out.println("\t04.MYCART");
                        System.out.println("\t05.MAIN MENU");
                        System.out.println("\t06.DELETE ITEMS IN CART");
                        y = scanner.nextInt();
                        switch (y) {
                            case 1:
                                bill();
                                break;
                            case 2:
                                invoice();
                                break;
                            case 3:
                                mdisplay();
                                break;
                            case 4:
                                mdisplay();
                                break;
                            case 5:
                                continue;
                            case 6:
                                mdelete();
                                break;
                            default:
                                System.out.println("Error");
                                break;
                        }
                    }
                case 3:
                    System.out.println("\tExiting...");
                    System.exit(0);
                default:
                    System.out.println("Error");
                    break;
            }
        }
    }
}
