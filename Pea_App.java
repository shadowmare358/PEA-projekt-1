

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * Klasa GUI 
 */
public class App extends JFrame implements ActionListener, Runnable {
	
	
	private static final long serialVersionUID = -6820018743229405568L;
	public JTextField nazwa_pliku = new JTextField();
	public JTextArea wyniki;
	JScrollPane wydruk_wynikow;
	JMenuItem loadFile;
	ArrayList<JMenuItem> Algorithms = new ArrayList<JMenuItem>();
	Plecak pl1;
	
	public App()
	{
		super("PEA Laboratorium ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(800,600));
		setResizable(false);
		setLocationRelativeTo(null);
	
		
		wyniki = new JTextArea();
		wydruk_wynikow = new JScrollPane(wyniki);
		JLabel bottom = new JLabel("<html><font color = 'green', size = 4>Autor: ; nr Indexu: ; ver 0.1</font></html> ");
		
		JMenuBar menu = new JMenuBar();
		
		JMenu filePanel = new JMenu("File");
		JMenu algorithmsPanel = new JMenu("Algorithms");
	
		loadFile = new JMenuItem("load File");
		for (Algorytm a : Algorytm.getAlgorithms())
		{
			JMenuItem i = new JMenuItem(a.getName());
			Algorithms.add(i);
			algorithmsPanel.add(i);
			i.addActionListener(this);
		}
		
		filePanel.add(loadFile);
		
		menu.add(filePanel);
		menu.add(algorithmsPanel);
		
		this.setJMenuBar(menu);
		
		this.add(wydruk_wynikow,BorderLayout.CENTER);
		this.add(bottom,BorderLayout.SOUTH);
		
		loadFile.addActionListener(this);

		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		
		// Przycisk to ladowania pliku
		if(src == loadFile)
		{
			JFileChooser jfc = new JFileChooser("./"); // browser do wybierania pliku - ustawiony na domyslna sciezke pliku wykonalnego
			jfc.showOpenDialog(this);
			if(jfc.getSelectedFile() == null) // error gdy nie zostanie wybrany plik, i przerwanie dalszych operacji
			{
				JOptionPane.showMessageDialog(this, "Nie wybrano pliku","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			pl1 = LadujDane.Load(jfc.getSelectedFile()); // przypisanie do obiektu plecaka ten wczytany z pliku
			if(pl1 == null) // jesli cos poszlo nie tak, to wyrzucenie bledu
				JOptionPane.showMessageDialog(this, "Blad wczytywania pliku","Error",JOptionPane.ERROR_MESSAGE);
			else
			{
				println("Zaladowano plik"); // wypisanie informacji o wczytanym plecaku
				println("Wielkosc plecaka: " + pl1.getCapacity());
				println("Dostepne przedmioty:");
				
				for(Przedmiot it : pl1.getAvailableItemz())
				{
					println("Waga: " + it.getWeight() + " , wartosc: " + it.getPrize());
				}
			}
		}
		else // wcisniecie przycisku z panelu algorytmow
		{
			if(pl1 != null) // jesli jest wczytany plik
			{
				println("---------------------------");
				Algorytm a = Algorytm.getByName(evt.getActionCommand()); // wybranie algorytmu z listy na bazie wcisnietego przycisku
				if(evt.getActionCommand().toString().equalsIgnoreCase("Dynamic Programming"))
				{
					//for( int i = 0;i < 1000; i++)
					//{
							Plecak tymczas = pl1.clone();
							println("");
							println("##### " + a.getName() + " #####");
							println("Maksymalna wartośc wszystkich przedmiotów: " + a.maxValue(tymczas));
							//a.maxValue(bpnew);
							println("Czas wykonania algorytmu: " + (double)a.getLastExecutionTime()/1000000000 + "s");
							//println("" +(double)a.getLastExecutionTime()/1000000000);
				//	}
				}
				else
				{
			//	for( int i = 0; i< 1000; i++)
			//	{
					Plecak bpnew = pl1.clone();
			//		a.perform(tymczas);
					println("");
					println("##### " + a.getName() + " #####");
					println("Optymalne zapelnienie plecaka:");
					for(Przedmiot its : a.perform(bpnew)) // wypisanie wyniku na JTextArea
						println("Waga: " + its.getWeight() + " , wartosc: " + its.getPrize());
					println("Czas wykonania algorytmu: " + (double)a.getLastExecutionTime()/1000000000 + "s");
				}
			//		println("" +(double)a.getLastExecutionTime()/1000000000);
			//	}
			}
			else
				println("Zaladuj plik!");
		}
	}
	public void print(String res) // metoda do skracania kodu
	{
		wyniki.setText(wyniki.getText() + res);
		wydruk_wynikow.getVerticalScrollBar().setValue(wydruk_wynikow.getVerticalScrollBar().getMaximum());
	}
	public void println(String res) // podobnie jak wyzej
	{
		print(res + "\n");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
