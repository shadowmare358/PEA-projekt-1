
import java.util.ArrayList;
import java.util.Collections;


public class Plecak implements Cloneable {

	private int capacity; // maksymalny udzwig
	private ArrayList<Przedmiot> availableItemz = new ArrayList<Przedmiot>(); // lista dostepnych przedmiotow
	
	public Plecak(int maxS) // przy tworzeniu plecaka, ustawiany jest jego maksymalny udzwig
	{
		this.capacity = maxS;
	}
	public Plecak(int cap,int items)
	{
		
		
	}
	public int getCapacity()
	{
		return this.capacity;
	}
	public boolean addItem(Przedmiot item)
	{
			availableItemz.add(item);
			return true;
	}
	public ArrayList<Przedmiot> getAvailableItemz()
	{
		return availableItemz;
	}
	
	// Sortowanie plecaka wg zaprojektowanego comparatora do obiektow klasy Item
	public void Sort()
	{
		Collections.sort(availableItemz,Porownanie.INSTANCE);
	}
	@Override
	public Plecak clone()
	{
		Plecak bp = new Plecak(capacity);
		bp.availableItemz = (ArrayList<Przedmiot>) availableItemz.clone();
		return bp;	
	}
	
	
}
