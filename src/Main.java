import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Relation r1 = new Relation("Persone.csv");
        Relation r2 = new Relation("Persone2.csv");
        Relation r3 = new Relation();

        Relation sel = r3.Selection(r1, "nome", "Mario");
        stampa(sel);

        ArrayList<String> keys = new ArrayList<>();
        keys.add("nome");
        keys.add("cognome");
        Relation pro = r3.Projection(r1, keys);
        stampa(pro);

        Relation uni = r3.Union(r1, r2);
        stampa(uni);

        Relation dif = r3.Difference(r1, r2);
        stampa(dif);

        Relation cart = r3.ProdCart(r2);
        stampa(cart);
    }

    public static void stampa(Relation r) {
        for (int i = 0; i < r.header.size(); i++) {
            System.out.println(r.header.get(i) + " ");
        }
        System.out.println();

        for (int i = 0; i < r.rows.size(); i++) {
            Row row =r.rows.get(i);
            for (int j = 0; j < row.values.size(); j++){
                System.out.println(row.values.get(j) + " ");
            }
            System.out.println();
        }
    }
}