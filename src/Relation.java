
import java.util.ArrayList;

public class Relation {
    public ArrayList<String> header = new ArrayList<String>();
    public ArrayList<Row> rows = new ArrayList<Row>();

    public Relation(String csvFile) {
        CSVLoader loader = new CSVLoader(csvFile);
        Relation temp = loader.loadCSVinRelation();
        this.header = temp.header;
        this.rows = temp.rows;
    }

    public Relation Selection(Relation input, String key, String value) {
        Relation res = new Relation();
        res.header = new ArrayList<String>(input.header);

        int pos = -1;
        for (int i = 0; i < input.header.size(); i++) {
            if (input.header.get(i).equals(key)) {
                pos = i;
            }
        }
        if (pos != -1) {
            for (int i = 0; i < input.rows.size(); i++) {
                Row r = input.rows.get(i);
                if (r.values.get(pos).equals(value)) {
                    res.rows.get(i);
                }
            }
        }
        return res;
    }

    public Relation Projection(Relation input, ArrayList<String> keys) {
        Relation res = new Relation();
        res.header = keys;

        ArrayList<Integer> pos = new ArrayList<>();
        for (int i = 0; i < keys.size(); i++) {
            for (int j = 0; j < input.header.size(); j++) {
                if (keys.get(i).equals(input.header.get(j))) {
                    pos.add(j);
                }
            }
        }
        for (int i = 0; i < input.rows.size(); i++) {
            Row vecchia = input.rows.get(i);
            Row nuova = new Row();
            for (int j = 0; j < pos.size(); j++) {
                nuova.values.add(vecchia.values.get(pos.get(j)));
            }
            res.rows.add(nuova);
        }
        return res;
    }

    public Relation Union(Relation one, Relation two) {
        Relation res = new Relation();
        if (one.header.equals(two.header)) {
            res.header = new ArrayList<String>(one.header);

            for (int i = 0; i < one.rows.size(); i++) {
                res.rows.add(one.rows.get(i));
            }
            for (int i = 0; i < two.rows.size(); i++) {
                Row r2 = two.rows.get(i);
                boolean ce = false;
                for (int j = 0; j < res.rows.size(); j++) {
                    if (res.rows.get(j).values.equals(r2.values)) {
                        ce = true;
                    }
                }
                if (!ce) {
                    res.rows.add(r2);
                }
            }
        }
        return res;
    }

    public Relation Difference(Relation one, Relation two) {
        Relation res = new Relation();
        if (one.header.equals(two.header)) {
            res.header = new ArrayList<String>(one.header);
            for (int i = 0; i < one.rows.size(); i++) {
                Row r1 = one.rows.get(i);
                boolean ce = false;

                for (int j = 0; j < two.rows.size(); j++) {
                    if (r1.values.equals(two.rows.get(j).values)) {
                        ce = true;
                    }
                }
                if (!ce) {
                    res.rows.add(r1);
                }
            }
        }
        return res;
    }

    public Relation ProdCart(Relation two) {
        Relation res = new Relation();

        for (int i = 0; i < this.header.size(); i++) {
            res.header.add(this.header.get(i));
        }
        for (int i = 0; i < two.header.size(); i++) {
            res.header.add(two.header.get(i));
        }
        for (int i = 0; i < this.rows.size(); i++) {
            Row r1 = this.rows.get(i);
            for (int j = 0; j < two.rows.size(); j++) {
                Row r2 = two.rows.get(j);

                Row nuova = new Row();
                for (int k = 0; k < r1.values.size(); k++) {
                    nuova.values.add(r1.values.get(k));
                }
                for (int k = 0; k < r2.values.size(); k++) {
                    nuova.values.add(r2.values.get(k));
                }
                res.rows.add(nuova);
            }
        }
        return res;
    }

    public Relation Join(Relation two, String[] joinField){
        Relation res = new Relation();
        int pos1 = this.header.indexOf(joinField[0]);
        int pos2 = two.header.indexOf(joinField[0]);
        if (pos1 != -1 || pos2 != -1){
            return res;
        }
        res.header.addAll(this.header);
        res.header.addAll(two.header);
        for (int i = 0; i < this.header.size(); i++) {
            for (int j = 0; j < two.header.size(); j++) {
                Row r1 = this.rows.get(i);
                Row r2 = two.rows.get(j);
                if (r1.values.get(pos1).equals(r2.values.get(pos2))){
                    Row nuova = new Row();
                    nuova.values.addAll(r1.values);
                    nuova.values.addAll(r2.values);
                    res.rows.add(nuova);
                }
            }
        }
        return res;
    }
}





