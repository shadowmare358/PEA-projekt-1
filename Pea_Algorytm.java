
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


/*
 * Klasa abstrakcyjna po ktorej dziedzicza wszystkie algorytmy
 */
public abstract class Algorytm {

	static HashMap<String,Algorytm> algs = new HashMap<String,Algorytm>(); // hashmapa do definiowania listy algorytmow - potrzebna do zmniejszenia kodu przy wiekszej ilosci algorytmow
	
	private long lastExecutionTime;
	
	protected final void startMeasure() {
	  lastExecutionTime = System.nanoTime();
	}
	protected final void stopMeasure() {
	  lastExecutionTime = System.nanoTime() - lastExecutionTime;
	}
	public long getLastExecutionTime() {
	  return lastExecutionTime;
	}
	
	protected Algorytm()
	{
		if(!algs.containsKey(getName())) // gdy juz nie ma takiego algorytmu to zaladowanie go do listy
			algs.put(getName(),this);
	}
	public abstract String getName(); // zwraca nazwe
	public abstract ArrayList<Przedmiot> perform(Plecak bp); // wywolanie algorytmu, zwraca liste juz z poprawnym rozwiazaniem
	public abstract int maxValue(Plecak bp);
	public static Collection<Algorytm> getAlgorithms() // zwraca kolekcje wszystkich algorytmow
	{
		return algs.values();
	}
	public static Algorytm getByName(String name) // dodatkowa metoda do pobierania obiektow Algorithm poprzez nazwe 
	{
		return algs.get(name);
	}
}
