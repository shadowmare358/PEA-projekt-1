
import java.util.Comparator;


/*
 * Klasa komparatora
 * Sortuje przedmioty w plecaku wg najlepszego profitu, do najgorszego
 */
public class Porownanie implements Comparator<Przedmiot> {

	public static final Porownanie INSTANCE = new Porownanie();
	@Override
	public int compare(Przedmiot i1, Przedmiot i2) {
		if(i1.Profit() > i2.Profit())
			return -1; 
		else if(i1.Profit() == i2.Profit())
			return 0;
		else
			return 1;
	}	
}
