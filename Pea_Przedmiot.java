
/*
 * KLasa przedmiotu
 */
public class Przedmiot {

	private int prize; // wartosc
	private int weight; // waga
	
	// przy tworzeniu przedmiotu przedmiotu, ustawiana jest jego wartosc i waga
	public Przedmiot(int size,int prize)
	{
		this.prize = prize;
		this.weight = size;
	}
	public int getWeight()
	{
		return this.weight;
	}
	public int getPrize()
	{
		return this.prize;
	}
	
	// profit, czyli cena przez wage - potrzebna do ustawienia listy wg alg zachlannego
	public float Profit()
	{
		return this.prize/(float)this.weight;
	}
}
