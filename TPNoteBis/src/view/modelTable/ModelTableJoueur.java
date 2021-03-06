package view.modelTable;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.metier.Joueur;

public class ModelTableJoueur extends AbstractTableModel {

	

	private final static String[] titres =  new String [] {   "code_joueur", "nom_joueur", "mail_joueur", "passwd_joueur"   };
	private final static Class[] types = new Class[] 	  {	   int.class, String.class, String.class, String.class};
	protected ArrayList<Joueur> joueurs;
	
	public ModelTableJoueur(){
		super();
		joueurs = new ArrayList<Joueur>();
	}
	
	public void setListe(ArrayList<Joueur> joueursSet){
		joueurs = joueursSet;
		this.fireTableDataChanged();
	}
	

	
	@Override
	public String getColumnName(int column) {
		return titres[column];
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}


	@Override
	public Class<?> getColumnClass(int arg0) {
		return types[arg0];
	}


	@Override
	public int getColumnCount() {
		return titres.length;
	}


	@Override
	public int getRowCount() {
		return joueurs.size();
	}


	@Override
	public Object getValueAt(int row, int column) {
		Object objet = null;
		
		switch (column){
		
		case 0: objet = joueurs.get(row).getCode();break;
		case 1: objet = joueurs.get(row).getNom();break;
		case 2: objet = joueurs.get(row).getMail();break;
		case 3: objet = joueurs.get(row).getPasswd();break;
		}
		
		return objet;
		
		
	}
}
