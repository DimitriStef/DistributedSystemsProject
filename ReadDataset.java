
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class ReadDataset {

    private ArrayList<BusLines> busLines = new ArrayList<>();
    private ArrayList<BusPositions> busPositions = new ArrayList<>();
    private ArrayList<RouteCodes> routeCodes = new ArrayList<>();

    public void readDataset() throws IOException {
        String dir = "dataset";
        //create file object
        File file = new File(dir);
        getDataFiles(file);
    }

    //get All lineID's from dataset
    public String[] getAllLineID() {
        String[] strArray = new String[busLines.size()];
        for (int i = 0; i < busLines.size(); i++) {
            strArray[i] = busLines.get(i).getLineID();
        }
        return strArray;
    }


    //read all txt files
    public void getDataFiles(File file) throws IOException {
        ReadDataset rd = new ReadDataset();
        BufferedReader breader = null;
        String fileName;

        for (File f : file.listFiles()) {
            fileName = f.getName();
            breader = new BufferedReader(new FileReader(f));
            String[] data;
            String line = breader.readLine();

            if (fileName.equals("busLinesNew.txt")) {
                while (line != null) {
                    data = line.trim().split("\\,");
                    busLines.add(rd.new BusLines(data[0], data[1], data[2]));
                    line = breader.readLine();
                }
            }
            if (fileName.equals("busPositionsNew.txt")) {
                while (line != null) {
                    data = line.trim().split("\\,");
                    busPositions.add(rd.new BusPositions(data[0], data[1], data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4]), data[5]));
                    line = breader.readLine();
                }
            }
            if (fileName.equals("RouteCodesNew.txt")) {
                while (line != null) {
                    data = line.trim().split("\\,");
                    routeCodes.add(rd.new RouteCodes(data[0], data[1], data[2], data[3]));
                    line = breader.readLine();
                }
            }
            breader.close();
        }
    }

    public void print(ArrayList<Object> object) {
        for (int i = 0; i < object.size(); i++) {
            System.out.println(object.get(i).toString());
        }
    }

    public ArrayList<BusLines> getBusLines() {
        return busLines;
    }

    public ArrayList<BusPositions> getBusPositions() {
        return busPositions;
    }

    public ArrayList<RouteCodes> getRouteCodes() {
        return routeCodes;
    }


    public class BusLines {
		/*Format: LineCode,LineId,DescriptionEnglish
		Perigrafi:
		LineCode: Unique id tis grammis
		LineId: Einai to busLineId i alliws to topic sto opoio anaferetai i ergasia
		DescriptionEnglish: To onoma tis grammis me latinikous xaraktires*/

        private String LineCode;
        private String LineID;
        private String DescriptionEnglish;

        BusLines(String LineCode, String LineID, String DescriptionEnglish) {
            this.LineCode = LineCode;
            this.LineID = LineID;
            this.DescriptionEnglish = DescriptionEnglish;
        }

        public String getLineCode() {
            return LineCode;
        }

        public String getLineID() {
            return LineID;
        }

        public String getDescriptionEnglish() {
            return DescriptionEnglish;
        }

        public String toString() {
            return "LineCode: '" + this.LineCode + "', LineID: '" + this.LineID + "', DescriptionEnglish: '" + this.DescriptionEnglish + "'";
        }
    }

    public class BusPositions {
		/*Format: LineCode,RouteCode,vehicleId,latitude,longitude,timestampOfBusPosition
		Perigrafi:
		LineCode: Unique id tis grammis
		RouteCode: UniqueId diadromis lewforeiou, diaforetiko apo afetiria pros terma kai apo terma pros afetiria (px gia tin grammi 821-> 1804 "Marasleios-Nea Kypseli" kai 1805 gia "Nea Kypseli-Marasleio")
		vehicleId: Unique id tou lewforeiou pou ektelei tin grammi
		latitude: geografiko platos
		longitude: geografiko mikos
		timestampOfBusPosition: xroniki stigmi pou estali i thesi apo ton aisthitira sto lewforeio*/

        String LineCode;
        String RouteCode;
        String vehicleID;
        double latitude;
        double longitude;
        String timestampOfBusPosition;

        BusPositions(String LineCode, String RouteCode, String vehicleID, double latitude, double longitude, String timestampOfBusPosition) {
            this.LineCode = LineCode;
            this.RouteCode = RouteCode;
            this.vehicleID = vehicleID;
            this.latitude = latitude;
            this.longitude = longitude;
            this.timestampOfBusPosition = timestampOfBusPosition;
        }

        public String toString() {
            return "LineCode: '" + this.LineCode + "', RouteCode: '" + this.RouteCode + "', vehicleID: '" + this.vehicleID + "'" + "', vehicleID'" + this.latitude + "', longitude: '" + this.longitude + "'" + "', timestampOfBusPosition: '" + this.timestampOfBusPosition + "'";
        }
    }

    public class RouteCodes {
		/*Format: RouteCode,LineCode,RouteType,DescriptionEnglish
		Perigrafi:
		RouteCode: UniqueId diadromis lewforeiou, diaforetiko apo afetiria pros terma kai apo terma pros afetiria (px gia tin grammi 821-> 1804 "Marasleios-Nea Kypseli" kai 1805 gia "Nea Kypseli-Marasleio")
		LineCode: Unique id tis grammis
		RouteType: an einai i diadromi apo afetiria pros terma i to anapodo
		DescriptionEnglish: To onoma tis grammis me latinikous xaraktires*/

        String RouteCode;
        String LineCode;
        String RouteType;
        String DescriptionEnglish;

        RouteCodes(String RouteCode, String LineCode, String RouteType, String DescriptionEnglish) {
            this.RouteCode = RouteCode;
            this.LineCode = LineCode;
            this.RouteType = RouteType;
            this.DescriptionEnglish = DescriptionEnglish;
        }

        public String toString() {
            return "RouteCode: '" + this.RouteCode + "', LineCode: '" + this.LineCode + "', RouteType: '" + this.RouteType + "', DescriptionEnglish: '" + this.DescriptionEnglish + "'";
        }
    }

}



































