package view.modelTable;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.metier.Question;

public class ModelTableQuestion  extends AbstractTableModel {

	

	private final static String[] titres =  new String [] {   "code_question", "texte_question", "reponse_question"   };
	private final static Class[] types = new Class[] 	  {	   int.class, String.class, String.class};
	private ArrayList<Question> questions;
	
	public ModelTableQuestion(){
		super();
		questions = new ArrayList<Question>();
	}
	
	public void setListe(ArrayList<Question> questionsSet){
		questions = questionsSet;
		this.fireTableDataChanged();
		
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
	public String getColumnName(int column) {
		return titres[column];
	}

	@Override
	public Object getValueAt (int row, int column) {
		
		Object objet = null;
		
		switch (column){
		
		case 0: objet = questions.get(row).getCode();break;
		case 1: objet = questions.get(row).getTexte();break;
		case 2: objet = questions.get(row).getReponse();break;
		}
		
		return objet;
		
	}

	@Override
	public int getColumnCount() {
		return titres.length;
	}

	@Override
	public int getRowCount() {
		return questions.size();
	}
}

