import java.util.*;
import java.util.Scanner;
import java.io.*;

public class Accident {
    private String id;
    private String date;
    private String time;
    private String severity;
    private String roadName;
    private String intersectionNumber;
    private String eventNature;
    public static void main(String [] args)
    {
        boolean cont = true;
        int choice=0;
        int accCount=0;
        // Assigning readNoLine() method to show the number of accidents in the datasheet.
        accCount = readNoLines();
        // Main Menu Loop
        do
        {
            // Main Menu Screen
            System.out.println("");
            System.out.println("");
            System.out.println("Welcome to the Main Roads WA crash data program. There are a total of "+ accCount +" accidents within this dataset.");
            System.out.println("");
            System.out.println("Please make a selection from the Menu below.");
            System.out.println("");
            System.out.println("");
            System.out.println("> 1. Examine data in relationship to a specified location.");
            System.out.println("");
            System.out.println("> 2. Examine the data generally.");
            System.out.println("");
            System.out.println("> 3. Exit Program");
            System.out.println("");
            System.out.println("");
            try {
                    // Main Menu Selection
                    Scanner checkChoice = new Scanner(System.in);
                    System.out.print("> ");
                    choice = checkChoice.nextInt();
                    System.out.println("");
                    System.out.println("");
                    
                    switch (choice) {
                        case 1:
                        // Calling relationLocation() method to display option 1 data
                            relationLocation();
                            break;
                        case 2:
                        // Calling examinData() method to display 2nd Menu
                            examinData();
                            break;
                        case 3:
                        // Display program terminating status and exits the program
                            System.out.println("");
                            System.out.println("");
                            System.out.println("Terminating Program...");
                            System.out.println("");
                            System.out.println("");
                            System.exit(0);
                            break;
                    
                        default:
                        // Input Error Status Indicator
                            System.out.println("");
                            System.out.println("");
                            System.out.println("Invalid Input! Please Re-enter!");
                            System.out.println("");
                            System.out.println("");
                            break;
                    }
                } catch (InputMismatchException | IOException e) {
                    //TODO: handle exception
                    System.out.println("");
                    System.out.println("");
                    System.out.println("Invalid Input! Please Re-enter!");
                    System.out.println("");
                    System.out.println("");
                    }
        }while (cont);

    }

    // Haversine method to calculate distance
    public static double haverSineDistance(double lat1, double lon1, double lat2, double lon2)
    {
        final int EARTHRADIUS = 6371; // Radious of the earth
        double latDistance = convertToRadians(lat2 - lat1);
        double lonDistance = convertToRadians(lon2 - lon1);
        double a = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) + (Math.cos(convertToRadians(lat1)) * Math.cos(convertToRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = EARTHRADIUS * c;
        return distance;
    }
    // Convert value to Rad value
    private static double convertToRadians(double value)
    {
        return value * Math.PI / 180;
    }

    // Shows the Menu option 1 processed data to screen
    public static void relationLocation()
    {
        // declaration of required variables 
        double latitude;
        double longitude;
        double maxLatitude;
        double maxLongitude;
        double minLatitude;
        double minLongitude;
        double furFatLat;
        double furFatLongit;
        double closFatLat;
        double closFatLongit;
        double furHostLat;
        double furHostLongit;
        double closHostLat;
        double closHostLongit;
        double tot10Acc;
        double perTot10Acc;
        double accCount;
        String max;
        String min;
        String fatMax;
        String fatMin;
        String hospMax;
        String hospMin;
        String locationName;
        System.out.println("");
        System.out.println("");
        System.out.println("The following details are required: Location latitude, location longitude and location name.");
        System.out.println("");
        Scanner locationInfo = new Scanner(System.in);
        // Asks the user for information to calculate output
        System.out.print("Latitude : ");
        latitude = locationInfo.nextDouble();
        System.out.println("");
        System.out.print("Longitude : ");
        longitude = locationInfo.nextDouble();
        System.out.println("");
        System.out.print("Location Name : ");
        locationInfo.nextLine();
        locationName = locationInfo.nextLine();
        System.out.println("");
        // Creating Location class object
        Location info = new Location();
        // Call the neccessary methods from Location class to calculate values
        max = String.valueOf(info.findMax());
        min = String.valueOf(info.findMin());
        fatMax = String.valueOf(info.findMaxCols(15, "Fatal"));
        fatMin = String.valueOf(info.findMinCols(15, "Fatal"));
        hospMax = String.valueOf(info.findMinCols(15, "Hospital"));
        hospMin = String.valueOf(info.findMinCols(15, "Hospital"));
        maxLatitude = info.findMaxLat(max);
        maxLongitude = info.findMaxLongit(max);
        minLatitude = info.findMinLat(min);
        minLongitude = info.findMinLongit(min);
        furFatLat = info.findLat(fatMax, locationName);
        furFatLongit = info.findLongit(fatMax, locationName);
        closFatLat = info.findLat(fatMin, locationName);
        closFatLongit = info.findLongit(fatMin, locationName);
        furHostLat = info.findLat(hospMax, locationName);
        furHostLongit = info.findLongit(hospMax, locationName);
        closHostLat = info.findLat(hospMin, locationName);
        closHostLongit = info.findLongit(hospMin, locationName);
        tot10Acc = info.find10Km();
        accCount = readNoLines();
        perTot10Acc = (tot10Acc/accCount)*100.0;
        // Display the calculated data directly with Haversine method return values
        System.out.println("Furthest accident from "+ locationName + " is " + String.format("%.2f", haverSineDistance(latitude, longitude, maxLatitude, maxLongitude)) + " away.");
        System.out.println("Closest accident to "+ locationName + " is " + String.format("%.2f", haverSineDistance(latitude, longitude, minLatitude, minLongitude)) + " away.");
        System.out.println("Furthest fatal accident from "+ locationName + " is " + String.format("%.2f", haverSineDistance(latitude, longitude, furFatLat, furFatLongit)) + " away.");
        System.out.println("Closest fatal accident to "+ locationName + " is " + String.format("%.2f", haverSineDistance(latitude, longitude, closFatLat, closFatLongit)) + " away.");
        System.out.println("Furthest hospital accident from "+ locationName + " is " + String.format("%.2f", haverSineDistance(latitude, longitude, furHostLat, furHostLongit)) + " away.");
        System.out.println("Closest hospital accident to "+ locationName + " is " + String.format("%.2f", haverSineDistance(latitude, longitude, closHostLat, closHostLongit)) + " away.");
        System.out.println("Total number of accidents within 10km of "+ locationName + " is " + tot10Acc );
        System.out.println("Percentage of all accidents within 10km of "+ locationName + " is " + String.format("%.2f",perTot10Acc) + "%");
        System.out.println("");
        System.out.println("");

    }

    // Displays the 2nd Menu options
    public static void examinData() throws IOException
    {
        int choice = 0;
        System.out.println("");
        System.out.println("");
        System.out.println("How would you like to see a summary of the data? Please make a selection from the Menu below.");
        System.out.println("");
        System.out.println("");
        System.out.println("> 1. Data displayed on the screen.");
        System.out.println("");
        System.out.println("> 2. Data displayed on the screen and written to file.");
        System.out.println("");
        System.out.println("> 3. Data written to file.");
        System.out.println("");
        System.out.println("");
        // 2nd Menu Selection
        Scanner checkChoice = new Scanner(System.in);
        System.out.print("> ");
        choice = checkChoice.nextInt();
        System.out.println("");
        System.out.println("");
        switch (choice) {
            case 1:
            // Calling the dataDisplay() method to display data to the screen
                dataDisplay();
                break;
            case 2:
            // Calling dataDisplayWrite() method to display and write the data to the screen and text file respectively
                dataDisplayWrite();
                break;
            case 3:
            // Calling dataWrite() method to write data to the text file
                dataWrite();
                break;
        
            default:
            // Input Error Status Indicator
                System.out.println("");
                System.out.println("");
                System.out.println("Invalid Input! Please Re-enter!");
                System.out.println("");
                System.out.println("");
                break;
        }
    }

    // Returns the number of accidents of the datasheet
    public static int readNoLines()
    {
        int count=-1;
        try {
        // BufferedReader to extract data from the datasheet
           BufferedReader br = new BufferedReader(new FileReader("../Crash_Information_(Last_5_Years).csv"));
            while((br.readLine()) != null) {
                count++;
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return count;
    }

    // Returns the number of accidents respective to the severity of the accident
    public static int readCsvInfo(String path, String searchString, int searchColumnIndex)
    {
        String line="";
        int count=0;
        try {
        // BufferedReader to extract data from the datasheet
           BufferedReader br = new BufferedReader(new FileReader(path));
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if(values[searchColumnIndex].equals(searchString)) {
                    count++;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return count;
    }

    // Display the proccess data of the types of accidents
    public static void dataDisplay() throws IOException
    {
        // Declaration of the required variables
        int accTot;
        double fatal;
        double hostp;
        double reAcc;
        double raAcc;
        double perFtlAcc;
        double perHostpAcc;
        double perReAcc;
        double perRaAcc;
        // Path of the datasheet
        String csvLoc = "./CrashData500.csv";
        // Calls the readNoLine() method to display the total number of accidents 
        accTot = readNoLines();
        // Calls the readCsvInfo to extract specific data from the datasheet
        fatal = readCsvInfo(csvLoc, "Fatal", 15);
        hostp = readCsvInfo(csvLoc, "Hospital", 15);
        reAcc = readCsvInfo(csvLoc, "Rear End", 16);
        raAcc = readCsvInfo(csvLoc, "Right Angle", 16);
        // Calculation of percentage values
        perFtlAcc = (fatal/accTot)*100.0;
        perHostpAcc = (hostp/accTot)*100.0;
        perRaAcc = (reAcc/accTot)*100.0;
        perReAcc = (raAcc/accTot)*100.0;
        System.out.println("");
        System.out.println("");
        // Display the processed data to the user 
        System.out.println("The total number of accidents: "+ accTot);
        System.out.println("The total number of fatal accidents: "+ fatal);
        System.out.println("The total number of fatal accidents as a percentage of all accidents: "+ String.format("%.2f", perFtlAcc) + "%");
        System.out.println("The total number of hospital accidents: "+ hostp);
        System.out.println("The total number of hospital accidents as a percentage of all accidents: "+ String.format("%.2f", perHostpAcc) + "%");
        System.out.println("The total number of Rear End accidents: "+ reAcc);
        System.out.println("The total number of Rear End accidents as a percentage of all accidents: "+ String.format("%.2f", perRaAcc) + "%");
        System.out.println("The total number of Right Angle accidents: "+ raAcc);
        System.out.println("The total number of Right Angle accidents as a percentage of all accidents: "+ String.format("%.2f", perReAcc) + "%");
        System.out.println("");
        System.out.println("");
        
    }
    // Writes the processed data to a file and display the data 
    public static void dataDisplayWrite()
    {
        // Declaration of the required variables
        int accTot;
        double fatal;
        double hostp;
        double reAcc;
        double raAcc;
        double perFtlAcc;
        double perHostpAcc;
        double perReAcc;
        double perRaAcc;
        // Path of the csv datasheet
        String csvLoc = "./CrashData500.csv";
        String fileName;
        // Calls the readNoLine() method to display the total number of accidents
        accTot = readNoLines();
        // Calls the readCsvInfo to extract specific data from the datasheet
        fatal = readCsvInfo(csvLoc, "Fatal", 15);
        hostp = readCsvInfo(csvLoc, "Hospital", 15);
        reAcc = readCsvInfo(csvLoc, "Rear End", 16);
        raAcc = readCsvInfo(csvLoc, "Right Angle", 16);
        // Calculation of percentage values
        perFtlAcc = (fatal/accTot)*100.0;
        perHostpAcc = (hostp/accTot)*100.0;
        perRaAcc = (reAcc/accTot)*100.0;
        perReAcc = (raAcc/accTot)*100.0;
        Scanner newScan = new Scanner(System.in);
        System.out.println("");
        System.out.println("");
        // Asks the user for a filename to print the data
        System.out.print("Please enter the file name : ");
        fileName = newScan.next();
        // File format creation and Error Handling
        try {
            File myObj = new File(fileName+".txt");
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            } else {
              System.out.println("File already exists.");
              return;
            }
            // FileWriter to write the data into the file
            FileWriter myWriter = new FileWriter(fileName+".txt");
            // Write the data into the file
            myWriter.write(("The total number of accidents: "+ accTot + "\n"));
            myWriter.write(("The total number of fatal accidents: "+ fatal + "\n"));
            myWriter.write(("The total number of fatal accidents as a percentage of all accidents: "+ String.format("%.2f", perFtlAcc) + "%" + "\n"));
            myWriter.write(("The total number of hospital accidents: "+ hostp + "\n"));
            myWriter.write(("The total number of hospital accidents as a percentage of all accidents: "+ String.format("%.2f", perHostpAcc) + "%" + "\n"));
            myWriter.write(("The total number of Rear End accidents: "+ reAcc + "\n"));
            myWriter.write(("The total number of Rear End accidents as a percentage of all accidents: "+ String.format("%.2f", perRaAcc) + "%" + "\n"));
            myWriter.write(("The total number of Right Angle accidents: "+ raAcc + "\n"));
            myWriter.write(("The total number of Right Angle accidents as a percentage of all accidents: "+ String.format("%.2f", perReAcc) + "%"));
            myWriter.close();
            System.out.println("");
            System.out.println("Successfully wrote to the file.");
            System.out.println("");
            // Calls dataDisplay to display the processed data to the user 
            dataDisplay();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

    // Only writes the data to the file
    public static void dataWrite()
    {
        // Declaration of required variables
        int accTot;
        double fatal;
        double hostp;
        double reAcc;
        double raAcc;
        double perFtlAcc;
        double perHostpAcc;
        double perReAcc;
        double perRaAcc;
        // Path to the CSV
        String csvLoc = "./CrashData500.csv";
        String fileName;
        Scanner newScan = new Scanner(System.in);
        // Calls the readNoLine() method to display the total number of accidents
        accTot = readNoLines();
        // Calls the readCsvInfo to extract specific data from the datasheet
        fatal = readCsvInfo(csvLoc, "Fatal", 15);
        hostp = readCsvInfo(csvLoc, "Hospital", 15);
        reAcc = readCsvInfo(csvLoc, "Rear End", 16);
        raAcc = readCsvInfo(csvLoc, "Right Angle", 16);
        // Calculation of percentage values
        perFtlAcc = (fatal/accTot)*100.0;
        perHostpAcc = (hostp/accTot)*100.0;
        perRaAcc = (reAcc/accTot)*100.0;
        perReAcc = (raAcc/accTot)*100.0;
        System.out.println("");
        System.out.println("");
        // Asks the user for a filename to print the data
        System.out.print("Please enter the file name : ");
        fileName = newScan.next();
        // File format creation and Error Handling
        try {
            File myObj = new File(fileName+".txt");
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            } else {
              System.out.println("File already exists.");
              return;
            }
            // FileWriter to write the data into the file
            FileWriter myWriter = new FileWriter(fileName+".txt");
            // Write the data into the file
            myWriter.write(("The total number of accidents: "+ accTot + "\n"));
            myWriter.write(("The total number of fatal accidents: "+ fatal + "\n"));
            myWriter.write(("The total number of fatal accidents as a percentage of all accidents: "+ String.format("%.2f", perFtlAcc) + "%" + "\n"));
            myWriter.write(("The total number of hospital accidents: "+ hostp + "\n"));
            myWriter.write(("The total number of hospital accidents as a percentage of all accidents: "+ String.format("%.2f", perHostpAcc) + "%" + "\n"));
            myWriter.write(("The total number of Rear End accidents: "+ reAcc + "\n"));
            myWriter.write(("The total number of Rear End accidents as a percentage of all accidents: "+ String.format("%.2f", perRaAcc) + "%" + "\n"));
            myWriter.write(("The total number of Right Angle accidents: "+ raAcc + "\n" ));
            myWriter.write(("The total number of Right Angle accidents as a percentage of all accidents: "+ String.format("%.2f", perReAcc) + "%"));
            myWriter.close();
            System.out.println("");
            System.out.println("Successfully wrote to the file.");
            System.out.println("");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
}
