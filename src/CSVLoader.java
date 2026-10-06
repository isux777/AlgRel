import java.io.BufferedReader;
import java.io.FileReader;

public class CSVLoader {
    String file = "";

    public CSVLoader(String file) {
        this.file = file;
    }

    public Relation loadCSVinRelation() {
        Relation loaded = new Relation();
        int rowNumber = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] campi = line.split(",");
                if (rowNumber == 0) {
                    for (int i = 0; i < campi.length; i++) {
                        loaded.header.add(campi[i].trim());
                    }
                } else {
                    Row r = new Row();
                    for (int i = 0; i < campi.length; i++) {
                        r.values.add(campi[i].trim());
                    }
                    loaded.rows.add(r);
                }
                rowNumber++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println("errore caricamento " + this.file);
        }

        return loaded;
    }
}
