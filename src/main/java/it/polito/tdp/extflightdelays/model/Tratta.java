package it.polito.tdp.extflightdelays.model;

public class Tratta {

	private int idAirport1;
	private int idAirport2;
	private int count;
	private int sum;
	

	public Tratta(int idAirport1, int idAirport2) {
		super();
		this.idAirport1 = idAirport1;
		this.idAirport2 = idAirport2;
		this.sum = 0;
		this.count = 0;
	}
	
	
	public int getIdAirport1() {
		return idAirport1;
	}



	public void setIdAirport1(int idAirport1) {
		this.idAirport1 = idAirport1;
	}



	public int getIdAirport2() {
		return idAirport2;
	}



	public void setIdAirport2(int idAirport2) {
		this.idAirport2 = idAirport2;
	}



	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}



	public int getSum() {
		return sum;
	}



	public void setSum(int sum) {
		this.sum = sum;
	}



	public void updateSum (int update) {
		sum += update;
		count++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAirport1;
		result = prime * result + idAirport2;
		return result;
	}

	
	public boolean equals(Object obj) {
		if (obj instanceof Tratta) {
			if (((Tratta) obj).getIdAirport1() == this.idAirport1 && ((Tratta) obj).getIdAirport2() == this.idAirport2) 
				return true;
			if (((Tratta) obj).getIdAirport2() == this.idAirport1 && ((Tratta) obj).getIdAirport1() == this.idAirport2)
				return true;
		}
		return false;
	}
	
	public String toString() {
		return "Aeroporto 1: "+idAirport1 +" Aeroporto 2: "+idAirport2+" Distanza Media: "+this.getAverage()+" miglia"; 
	}

	public int getAverage() {
		return sum/count;
	}
	
	

	
	
	
}
