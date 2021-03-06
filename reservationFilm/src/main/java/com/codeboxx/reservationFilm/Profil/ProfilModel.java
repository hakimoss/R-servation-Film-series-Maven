package com.codeboxx.reservationFilm.Profil;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ProfilModel extends AbstractTableModel {
	
	private String[] nomColonnes=new String[] {"id", "NOM", "Score"};
	private List<String[]> tableValues=new ArrayList<String[]>();

	public int getRowCount() {
		// TODO Auto-generated method stub
		return tableValues.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return nomColonnes.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return tableValues.get(rowIndex)[columnIndex];
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return nomColonnes[column];
	}
	
	public void setData(List<Profil> profils) {
		tableValues = new ArrayList<String[]>();
		for(Profil p:profils) {
			tableValues.add(new String[] {
				""+p.getIdProfil(),
				p.getName(),
				""+p.getScore(),
		
			});
		}
		fireTableChanged(null);
	}

}
