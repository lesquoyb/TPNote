package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.metier.Question;

public class DAOQuestion extends DAO<Question> {

	public DAOQuestion(Connection con) {
		super(con);
	}

	/**
	 * Retourne un objet Question correspondant au code donn�, retourne null s'il n'existe pas.
	 * @param code
	 * @return
	 */
	public Question get(int code) {
		String requete = "SELECT * FROM question WHERE code_question =?";
		Question retour = null;
		try {
			PreparedStatement rq = connection.prepareStatement(requete);
			rq.setInt(1, code);
			ResultSet res = rq.executeQuery();
			if (res.next()){
				retour = new Question(res.getInt("code_question"), 
									res.getString("texte_question"),
									res.getString("reponse_question"));
			}
			fermerStatement(rq);
			fermerResultat(res);

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return retour;
	}

	/**
	 * Retourne la liste de toutes les question dont l'�nonc� contient l'argument pass� en param�tre.
	 * @param expression
	 * @return
	 */
	public ArrayList<Question> listeQuestion(String expression){
		ArrayList<Question>liste= new ArrayList<Question>();
		String requete = "SELECT * FROM question WHERE texte_question LIKE ?";
		try {
			PreparedStatement prep = connection.prepareStatement(requete);
			prep.setString(1, "%"+expression+"%");
			ResultSet res = prep.executeQuery();
			while(res.next()){
				liste.add(new Question(res.getInt("code_question"), 
									res.getString("texte_question"),
									res.getString("reponse_question")));
			}

			fermerStatement(prep);
			fermerResultat(res);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}
	
	/**
	 * Retourne une liste contenant toutes les questions de la bdd.
	 * @return
	 */
	@Override
	public ArrayList<Question> getAll(){
		ArrayList<Question>liste= new ArrayList<Question>();
		String requete = "SELECT * FROM question";
		try {
			PreparedStatement prep = connection.prepareStatement(requete);
			ResultSet res = prep.executeQuery();
			while(res.next()){
				liste.add(new Question(res.getInt("code_question"), 
									res.getString("texte_question"),
									res.getString("reponse_question")));
			}
			fermerStatement(prep);
			fermerResultat(res);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}
	
	/**
	 * Retourne le nombre de questions dans la base de donn�es.
	 * @return
	 */
	public int nombreQuestions(){
		String requete = "SELECT COUNT(*) FROM question";
		int retour = 0;
		try{
			PreparedStatement prep = connection.prepareStatement(requete);
			ResultSet res = prep.executeQuery();
			if (res.next()){
				retour = res.getInt(1);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 * Ajoute un objet de type Question � la base de donn�es.
	 * @param objet
	 */
	@Override
	public void insert(Question objet) throws SQLException {
		String requete = "INSERT INTO question (texte_question,reponse_question) VALUES(?,?)";
		PreparedStatement prep = connection.prepareStatement(requete,Statement.RETURN_GENERATED_KEYS);
		prep.setString(1, objet.getTexte());
		prep.setString(2, objet.getReponse());
		prep.executeUpdate();
		ResultSet clef = prep.getGeneratedKeys();
		clef.next();
		objet.setCode(clef.getInt(1));
		fermerStatement(prep);
	}

	@Override
	public void delete(Question objet) throws SQLException {
		if (objet != null && objet.getCode() != -1){
			try {
				String requete ="DELETE FROM question WHERE code_question=?";
				PreparedStatement prep = connection.prepareStatement(requete);
				prep.setInt(1, objet.getCode());
				prep.executeUpdate();
				fermerStatement(prep);
				objet.setCode(-1);
			} 
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	

}
