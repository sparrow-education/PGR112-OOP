package lib.bookloan.controller;


import lib.bookloan.model.dto.book.*;
import lib.bookloan.model.dto.user.User;
import lib.bookloan.model.dto.user.UserRepositoryMenu;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;


public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private static LoanRecordRepository loanRepo = new LoanRecordRepository();
    private static BookRepository bookRepo = new BookRepository();
    private static UserRepositoryMenu userRepo = new UserRepositoryMenu();
    private static ArrayList<User> userRetrievedFromDb = new ArrayList<>();
    private static int isRentingNormal = 0;
    private static int subscribe = 0;
    private static int freeTrials = 0;
    private static ArrayList<NormalBook> normalBookInStock = new ArrayList<>();
    private static ArrayList<AcousticBook> acousticBookInStock = new ArrayList<>();

    public static void userStart() {
        System.out.println("Welcome!");
        System.out.print("Sign in: ");
        String name = scanner.nextLine();
        ArrayList<User> userList = userRepo.canRead();

        for (User u : userList) {
            if (name.strip().equalsIgnoreCase(u.getName())) {
                String userConcat = name.substring(0, 1).toUpperCase().concat(name.substring(1));
                System.out.println("Welcome back " + userConcat);
                userRetrievedFromDb.add(new User(name));
            }
        }
        if (userRetrievedFromDb.size() != 1) {
            System.out.println("Couldn't find " + name);
            User newUser = null;
            try {
                newUser = userRepo.canInsert(new User(name));
            } catch (Exception e) {
                System.out.println("Already exists " + e.getMessage());
                userStart();
            }
            if (newUser != null) System.out.println("Welcome " + newUser);
        }

        System.out.println("Do you want to subscribe?: \n Y/N");
        boolean run = true;
        while (run) {
            String member = scanner.nextLine();
            switch(member.strip().toLowerCase())
            {
                case "y" -> {
                    if ((subscribe != 1)) {
                        subscribe = 1;
                        freeTrials = 0;
                        System.out.printf("[Member: %d] [Trial: %d]%n",subscribe, freeTrials);
                        mainMenu();
                    }
                }
                case "n" -> {
                    if (subscribe == 0)
                        freeTrials = 2;
                        System.out.printf("[Member: %d] [Trial: %d]%n",subscribe, freeTrials);
                        mainMenu();
                }
                default -> {
                    System.out.println("\nuserStart: Not an option\n");
                    run = false;
                    userStart();
                }
            }
        }
        System.exit(0);
    }


    public static void mainMenu() {
        String mainMenuTxt = """
                    
                    1. Show Main
                    2. Show library menu
                    3. Exit
                    
                """;
        System.out.println(mainMenuTxt);
        boolean run = true;
        while (run) {
            String input = scanner.nextLine();
            switch(input) {
                case "1" -> mainMenu();
                case "2" -> crudMenu();
                case "3" -> run = false;
                default -> {
                    System.out.println("\nMain: Not an option\n");
                    mainMenu();
                }
            }
        }
        System.out.println("Exit");
        System.exit(0);
    }



    public static void crudMenu() {
        String crudMenuTxt = """
                    c. Create User
                    1. Loan normal book
                    2. Loan acoustic book
                    3. Show normal books
                    4. Show acoustic books
                    5. Exit
                    
                """;
        System.out.println(crudMenuTxt);
        UserRepositoryMenu userRepo = new UserRepositoryMenu();
        userRepo.canCreateDatabase();

        boolean run = true;
        while (run) {
            String input = scanner.nextLine();
            switch(input){
                case "a" -> {
                    // Fetching from ResultSet object to display "current book library"
                    if (isRentingNormal == 1) {
                        System.out.println("Return your current book\n");
                        crudMenu();
                    } else {
                        normalBookInStock = bookRepo.canReadNormalBook();
                        System.out.println("+----------+----------+----------+----------+");
                        normalBookInStock.forEach(normalBook -> System.out.printf("id %d, author %s, available %d%n",normalBook.getId(),normalBook.getAuthor(),normalBook.getLoanPeriod()));
                        System.out.println("+----------+----------+----------+----------+");

                        boolean badNumber = false;
                        do {
                            System.out.printf("Enter id: ");
                            try {
                                int id = scanner.nextInt();
                                ArrayList<Integer> currentlyRenting = new ArrayList<>();
                                currentlyRenting.add(id); //This is equivalent to bookId
                                int rentCount = loanRepo.canRent(currentlyRenting);//updating jdbc taking arraylist as arg

                                if (rentCount == 1) {
                                    isRentingNormal = 1;
                                    for (NormalBook nb : normalBookInStock) {
                                        if (nb.getLoanPeriod() == 0) System.out.printf("Renting -> id: %d, availability: %d%n",nb.getId(),nb.getLoanPeriod());
                                    }
                                    badNumber = false;
                                } else if (rentCount == 0) isRentingNormal = 0;
                            } catch (Exception e) {
                                System.out.println("Bad number");
                                scanner.next();
                                badNumber = true;
                            }
                        } while (badNumber);


                    }

                }
                case "b" -> {
                    boolean badNumber;
                    if (isRentingNormal == 1) {
                        for (NormalBook nb : normalBookInStock) {
                            if (nb.getLoanPeriod() == 0) System.out.printf("id: %d, availability: %d%n",nb.getId(),nb.getLoanPeriod());
                        }

                        do {
                            System.out.printf("Enter id: ");
                            try{
                                int id = scanner.nextInt();
                                ArrayList<Integer> currentlyReturning = new ArrayList<>();
                                currentlyReturning.add(id);
                                int returnCount = loanRepo.canReturn(currentlyReturning);
                                if (returnCount > 0) isRentingNormal = 0;
                                badNumber = false;
                            } catch (Exception e) {
                                System.out.println("Bad number");
                                scanner.next();
                                badNumber = true;
                            }
                        } while(badNumber);
                    } else {
                        System.out.println("Nothing to return");
                        System.out.println(crudMenuTxt);
                    }
                }

                case "c" -> {
                    //User user = userRepo.canInsert(new User());
                    //userRetrievedFromDb.add(user);
                }
                case "1" -> {
                    if (isRentingNormal == 1) {
                        System.out.println("Deliver book back first");
                        break;
                    }
                    int rentCounter = loanRepo.canRentNormBook();
                    if (rentCounter == 1) {
                        isRentingNormal = 1;
                    } else if (rentCounter == 0) {
                        isRentingNormal = 0;
                    }
                    System.out.printf("Is currently renting %d%n",isRentingNormal);
                    System.out.println(crudMenuTxt);
                }
                case "2" -> {
                    if (freeTrials == 0) {
                        System.out.println("No more trials!");
                        break;
                    } else if (subscribe == 0 ) {
                        freeTrials--;
                        acousticBookInStock = bookRepo.canReadAcousticBook();
                        System.out.println("+----------+----------+----------+----------+");
                        acousticBookInStock.forEach(acousticBook -> System.out.printf("id %d, author %s, trials %d%n", acousticBook.getId(),acousticBook.getAuthor(),acousticBook.getFreeTrialPeriod()));
                        System.out.println("+----------+----------+----------+----------+");
                    }
                    boolean badNumber = false;
                    do {
                        System.out.print("Enter book id: ");
                        try {
                            ArrayList<Integer> trials = new ArrayList<>();
                            trials.add(freeTrials);

                            int id = scanner.nextInt();
                            ArrayList<Integer> renting = new ArrayList<>();
                            renting.add(id);

                            int rentCounter = loanRepo.canRentAcoBook(trials, renting);
                            for (AcousticBook ab : acousticBookInStock) {
                                if (ab.getFreeTrialPeriod() < 2) System.out.printf("Renting -> id: %d author: %s%n",ab.getId(),ab.getAuthor());
                            }




                            badNumber = false;
                            System.out.println(freeTrials);
                            System.out.println(crudMenuTxt);
                        } catch (Exception e) {
                            System.out.println("Bad number");
                            scanner.next();
                            badNumber = true;
                        }
                    } while (badNumber);


//                    if (freeTrials == 0) {
//                        System.out.println("No more trials");
//                        break;
//                    }
//                    int rentCounter = loanRepo.canRentAcoBook();
//                    if ((subscribe == 0) && rentCounter == 1) {
//                        freeTrials--;
//                    }
//                    else if ((subscribe == 1) && rentCounter == 1 ) {
//                        continue;
//                    }
                    System.out.println(freeTrials);
                    System.out.println(crudMenuTxt);
                }
                case "3" -> {
                    ArrayList<NormalBook> normalBooks = bookRepo.canReadNormalBook();
                    for (NormalBook nb : normalBooks) System.out.println(nb);
                    System.out.println(crudMenuTxt);
                }
                case "4" -> {
                    ArrayList<AcousticBook> acousticBooks = bookRepo.canReadAcousticBook();
                    for (AcousticBook ab : acousticBooks) System.out.println(ab);
                    System.out.println(crudMenuTxt);
                }
                case "5" -> run = false;
                default -> {
                    //System.out.println("\nOperands: Not an option\n");
                    System.out.println(crudMenuTxt);
                }
            }
        }
        mainMenu();
    }
}
