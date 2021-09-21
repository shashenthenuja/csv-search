import java.io.*;

public class Location {
    private double latitude;
    private double longitude;
    private String roadNumber;

    // Finds the max value of the SLK column
    public double findMax()
    {
        String line="";
        double maxValue = 0.0;
        try {
            // BufferedReader to extract data from the datasheet
           BufferedReader br = new BufferedReader(new FileReader("./CrashData500.csv"));
           line = br.readLine();
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Assign the extracted column data to currenValue variable
                double currentValue = Double.parseDouble(values[8]);
                // Find the max value from the data
                if(currentValue< maxValue) {
                   maxValue = currentValue; 
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return maxValue;
    }

    // Finds the min value of the SLK column
    public double findMin()
    {
        String line="";
        double minValue = 0.0;
        try {
            // BufferedReader to extract data from the datasheet
           BufferedReader br = new BufferedReader(new FileReader("./CrashData500.csv"));
           line = br.readLine();
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Assign the extracted column data to currenValue variable
                double currentValue = Double.parseDouble(values[8]);
                if(currentValue> minValue) {
                    // Find the min value from the data
                   minValue = currentValue;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return minValue;
    }

    // Display the minimum value from the data with min SLK value corresponding to searchString
    public double findMinCols(int colIndex, String searchString)
    {
        String line="";
        double minValue = 0.0;
        try {
            // BufferedReader to extract data from the datasheet
           BufferedReader br = new BufferedReader(new FileReader("./CrashData500.csv"));
           line = br.readLine();
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Assign the extracted column data to currenValue variable
                double currentValue = Double.parseDouble(values[8]);
                // Find the min value from the data with the corresponding searchString
                if(currentValue> minValue && values[colIndex].equals(searchString)) {
                   minValue = currentValue;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return minValue;
    }
    // Display the max value from the data with max SLK value corresponding to searchString
    public  double findMaxCols(int colIndex, String searchString)
    {
        String line="";
        double maxValue = 0.0;
        try {
            // BufferedReader to extract data from the datasheet
           BufferedReader br = new BufferedReader(new FileReader("./CrashData500.csv"));
           line = br.readLine();
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Assign the extracted column data to currenValue variable
                double currentValue = Double.parseDouble(values[8]);
                // Find the max value from the data with the corresponding searchString
                if(currentValue< maxValue && values[colIndex].equals(searchString)) {
                   maxValue = currentValue;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return maxValue;
    }

    // Display the latitude value corresponding to the maxMin value
    public double findMaxLat(String maxMinVal)
    {
        String line="";
        double lat = 0.0;
        try {
            // BufferedReader to extract data from the datasheet
           BufferedReader br = new BufferedReader(new FileReader("./CrashData500.csv"));
           line = br.readLine();
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Find the latitude value from the data with the corresponding max value
                if ((values[8].contains(maxMinVal)))
                {
                    lat = Double.parseDouble(values[12]);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return lat;
    }

    // Display the longitude value corresponding to the maxMin value
    public double findMaxLongit(String maxMinVal)
    {
        String line="";
        double longit = 0.0;
        try {
            // BufferedReader to extract data from the datasheet
           BufferedReader br = new BufferedReader(new FileReader("./CrashData500.csv"));
           line = br.readLine();
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Find the longitude value from the data with the corresponding max value
                if ((values[8].contains(maxMinVal)))
                {
                    longit = Double.parseDouble(values[11]);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return longit;
    }

    // Display the latitude value corresponding to the maxMin value
    public double findMinLat(String maxMinVal)
    {
        String line="";
        double lat = 0.0;
        try {
            // BufferedReader to extract data from the datasheet
           BufferedReader br = new BufferedReader(new FileReader("./CrashData500.csv"));
           line = br.readLine();
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Find the latitude value from the data with the corresponding min value
                if ((values[8].contains(maxMinVal)))
                {
                    lat = Double.parseDouble(values[12]);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return lat;
    }

    // Display the longitude value corresponding to the maxMin value 
    public double findMinLongit(String maxMinVal)
    {
        String line="";
        double longit = 0.0;
        try {
            // BufferedReader to extract data from the datasheet
           BufferedReader br = new BufferedReader(new FileReader("./CrashData500.csv"));
           line = br.readLine();
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Find the longitude value from the data with the corresponding min value
                if ((values[8].contains(maxMinVal)))
                {
                    longit = Double.parseDouble(values[11]);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return longit;
    }

    // Display the latitude value corresponding to the maxMin value and locationName
    public double findLat(String maxMinVal, String locName)
    {
        String line="";
        double lat = 0.0;
        try {
            // BufferedReader to extract data from the datasheet
           BufferedReader br = new BufferedReader(new FileReader("./CrashData500.csv"));
           line = br.readLine();
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Find the latitude value from the data with the corresponding location name from user
                if ((values[8].contains(maxMinVal)) && (values[6].contains(locName)))
                {
                    lat = Double.parseDouble(values[12]);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return lat;
    }

    // Display the longitude value corresponding to the maxMin value and locationName
    public double findLongit(String maxMinVal, String locName)
    {
        String line="";
        double longit = 0.0;
        try {
            // BufferedReader to extract data from the datasheet
           BufferedReader br = new BufferedReader(new FileReader("./CrashData500.csv"));
           line = br.readLine();
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Find the longitude value from the data with the corresponding max value
                if ((values[8].contains(maxMinVal)) && (values[6].contains(locName)))
                {
                    longit = Double.parseDouble(values[11]);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return longit;
    }

    // Display the number of accidents from the data less than 10 KM 
    public int find10Km()
    {
        String line="";
        int count=0;
        double val=10.0;
        try {
        // BufferedReader to extract data from the datasheet
           BufferedReader br = new BufferedReader(new FileReader("./CrashData500.csv"));
           line = br.readLine();
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Assign the extracted column data to currenValue variable
                double currentValue = Double.parseDouble(values[8]);
                // Find the number of accidents from the data less than 10 KM 
                if((currentValue < val)) {
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
}
